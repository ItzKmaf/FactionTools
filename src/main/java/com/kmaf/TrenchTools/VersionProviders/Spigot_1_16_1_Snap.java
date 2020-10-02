package com.kmaf.TrenchTools.VersionProviders;

import com.kmaf.TrenchTools.GUI.Menu;
import com.kmaf.TrenchTools.VersionProvider;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sun.swing.SwingUtilities2;

import java.util.ArrayList;
import java.util.Arrays;

public class Spigot_1_16_1_Snap implements VersionProvider, Listener {
	
	private ArrayList<Menu> menus = new ArrayList<>();
	private static ArrayList<Material> glass_colors = new ArrayList<>(Arrays.asList(Material.WHITE_STAINED_GLASS_PANE, Material.ORANGE_STAINED_GLASS_PANE));
	@Override
	public boolean openMenu(Menu menu, Player player) {
		Inventory inventory = Bukkit.createInventory(null, menu.getMenuSize(), menu.getMenuName());
		ItemStack item = new ItemStack(glass_colors.get(menu.getBackgroundColor()));
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		for (int slot = 0; slot < menu.getMenuSize(); slot++) {
			if (inventory.getItem(slot) == null) {
				inventory.setItem(slot, item);
			}
		}
		player.openInventory(inventory);
		addMenu(menu);
		return true;
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		for (Menu menu: menus) {
			if (menu.getMenuName().equals(e.getView().getTitle())) {
				e.setCancelled(true);
				menu.onInventoryClickEvent(e);
				break;
			}
		}
	}
	
	public boolean checkClickedItem(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (item == null) {
			return false;
		}
		if (item.getType() != Material.AIR) {
			return false;
		}
		if (!item.hasItemMeta()) {
			return false;
		}
		if (item.getItemMeta() == null) {
			return false;
		}
		if (!item.getItemMeta().hasDisplayName()) {
			return false;
		}
		return true;
	}
	
	public String getClickedItemName(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (item == null) {
			throw new IllegalArgumentException("Clicked Item cannot be null");
		}
		if (!e.getCurrentItem().getItemMeta().hasDisplayName()) {
			throw new IllegalArgumentException("Clicked item's display name cannot be empty");
		}
		return e.getCurrentItem().getItemMeta().getDisplayName();
	}
	
	private void addMenu(Menu newMenu) {
		for (Menu menu: menus) {
			if (menu.getMenuName().equals(newMenu.getMenuName())) {
				return;
			}
		}
		menus.add(newMenu);
	}
}
