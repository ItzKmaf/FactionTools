package com.kmaf.TrenchTools.Tools.TrenchTools;

import com.kmaf.TrenchTools.Tools.Tool;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class TrenchTool implements Tool {
	
	private final String displayName;
	private final int diameter;
	/**
	 * Creates A trench tool from a builder.
	 * @param builder The Instance of Builder
	 */
	public TrenchTool(Builder builder) {
		this.displayName = builder.displayName;
		this.diameter = builder.diameter;
	}
	
	@Override
	public ItemStack getItemStack() {
		return null;
	}
	
	@Override
	public String getDisplayName() {
		return null;
	}                         
	
	public static class Builder {
		
		private String displayName = null;
		private int diameter = -1;
		private String configName = null;
		
		/**
		 * Sets the name of the tool.
		 * @param displayName The displayName of the tool being Built.
		 *             Cannot be null or empty (After trimming)
		 */
		public void setDisplayName(String displayName) {
			if (displayName == null) {
				throw new NullPointerException("Name of tool cannot be null.");
			}
			if (displayName.trim().equals("")) {
				throw new IllegalArgumentException("Name of tool cannot be empty. (\"\")");
			}
			this.displayName = ChatColor.translateAlternateColorCodes('&',displayName);
		}
		
		/**
		 * Sets the diameter of the tool.
		 * @param diameter The diameter of the tool being Built.
		 *                 Cannot be below one, above 50 or even.
		 */
		public void setDiameter(int diameter) {
			if (diameter <= 0) {
				throw new IllegalArgumentException("Diameter of tool cannot be below 1");
			}
			if (diameter > 50) {
				throw new IllegalArgumentException("Diameter of tool cannot be above 50");
			}
			if ((diameter % 2) != 1) {
				throw new IllegalArgumentException("Diameter of tool cannot be odd.");
			}
			this.diameter = diameter;
		}
		
		public void setConfigName(String configName) {
			if (configName == null) {
				throw new NullPointerException("Configuration Name of tool cannot be null.");
			}
			if (configName.trim().equals("")) {
				throw new IllegalArgumentException("Configuration Name of tool cannot be empty. (\"\")");
			}
			if (!configName.replace(" ", "").equals(configName)) {
				throw new IllegalArgumentException("Configuration Name of tool cannot include spaces");
			}
			this.configName = configName;
		}
		
		
		/**
		 * Creates and returns an instance of TrenchTool with all values preset.
		 * @return TrenchTool object with the values set.
		 * @throws IllegalStateException If the name or diameter of the tool were not set prior to invoking this method.
		 */
		public TrenchTool build() throws IllegalStateException{
			if (displayName == null) {
				throw new IllegalStateException("Name must be set before Tool can be built.");
			}
			if (diameter == -1) {
				throw new IllegalStateException("Diameter must be set before Tool can be built.");
			}
			if (configName == null) {
				throw new IllegalStateException("Configuration Name must be set before Tool can be built.");
			}
			return new TrenchTool(this);
		}
	}
}
