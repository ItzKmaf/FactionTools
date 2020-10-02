package com.kmaf.TrenchTools.GUI;

import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.Logger;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import sun.plugin.dom.exception.BrowserNotSupportedException;

import java.util.ArrayList;

public class Menu {
	
	private VersionProvider provider;
	
	private final String menuName;
	private final int backgroundColor;
	private int size = 3;
	private final ArrayList<Option> options;
	private final CLOSE_BEHAVIOR closeBehavior;
	
	private Menu(Builder builder) {
		this.provider = builder.provider;
		this.menuName = builder.menuName;
		this.backgroundColor = builder.backgroundColor;
		this.options = builder.options;
		this.closeBehavior = builder.closeBehavior;
		this.size = (builder.rows * 9);
	}
	
	public String getMenuName() {
		return menuName;
	}
	
	public int getMenuSize() {
		return size;
	}
	
	public int getBackgroundColor() {
		return backgroundColor;
	}
	
	public ArrayList<Option> getOptions() {
		return options;
	}
	
	public CLOSE_BEHAVIOR getCloseBehavior() {
		return closeBehavior;
	}
	
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (!provider.checkClickedItem(e)) {
			return;
		}
		String displayName = provider.getClickedItemName(e);
		for (Option option: options) {
			if (option.getDisplayName().equals(displayName)) {
				if (e.getWhoClicked() instanceof Player) {
					option.onClick((Player) e.getView().getPlayer());
				}
				break;
			}
		}
	}
	
	public static class Builder {
		
		private FactionTools main;
		private VersionProvider provider;
		private String menuName = null;
		private int backgroundColor = 0;
		private int rows = 3;
		private ArrayList<Option> options = new ArrayList<>();
		private CLOSE_BEHAVIOR closeBehavior = CLOSE_BEHAVIOR.CLOSE_TO_GAME;
		
		public Builder(FactionTools main, VersionProvider provider) {
			this.main = main;
			this.provider = provider;
		}
		
		
		/**
		 * Sets the name of the GUI
		 * @param menuName The name of the GUI
		 * @throws NullPointerException if the name of the tool is null
		 * @throws IllegalArgumentException if the name of the tool after trimming is empty
		 */
		public void setMenuName(String menuName) {
			if (menuName == null) {
				throw new NullPointerException("Name of tool cannot be null.");
			}
			if (menuName.trim().equals("")) {
				throw new IllegalArgumentException("Name of tool cannot be empty. (\"\")");
			}
			this.menuName = ChatColor.translateAlternateColorCodes('&', menuName);
		}
		
		/**
		 * Sets the background color of the GUI
		 * @param backgroundColor the color of the GUI (0 to 15)
		 * @throws IllegalArgumentException if the number is below 0 or above 15
		 */
		public void setBackgroundColor(int backgroundColor) {
			if (backgroundColor < 0) {
				throw new IllegalArgumentException("Color of tool cannot be smaller than 0");
			}
			if (backgroundColor > 15) {
				throw new IllegalArgumentException("Color of the tool cannot be larger than 15");
			}
		}
		
		/**
		 * Sets the number of rows in the GUI
		 * @param rows the number of rows in the GUI
		 * @throws IllegalArgumentException if the number is below 1 or above 6
		 */
		public void setRows(int rows) {
			if (rows < 0) {
				throw new IllegalArgumentException("Number of rows must be larger than 0");
			}
			if (rows > 6) {
				throw new IllegalArgumentException("Number of rows cannot be larger than 6");
			}
			this.rows = rows;
		}
		
		public Menu build() {
			if (menuName == null) {
				throw new IllegalStateException("Menu Name must be set before Menu can be built.");
			}
			
			if (options.isEmpty()) {
				main.getConsoleLogger().logToConsole("{plugin-name}: &8&l[&c&lWARNING&8&l] &7Menu: &c" + menuName + " has no options defined.");
				main.getConsoleLogger().logToConsole("This is likely to be an error. Check your config or contact the developer for more information.");
			}
			
			return new Menu(this);
		}
	}
	
	
	public enum CLOSE_BEHAVIOR {
		CLOSE_TO_GAME,
		CLOSE_TO_MENU,
		DENY_CLOSE,
		SEND_CHAT
	}
}
