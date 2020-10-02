package com.kmaf.TrenchTools;

import com.kmaf.TrenchTools.GUI.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface VersionProvider {
	
	boolean openMenu(Menu menu, Player player);
	boolean checkClickedItem(InventoryClickEvent e);
	String getClickedItemName(InventoryClickEvent e);
}
