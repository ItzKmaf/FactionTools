package com.kmaf.TrenchTools.GUI;

import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.Logger;

import java.util.ArrayList;

public class Menu {
	
	private final String menuName;
	private final int backgroundColor;
	private final ArrayList<Option> options;
	private final CLOSE_BEHAVIOR closeBehavior;
	
	private Menu(Builder builder) {
		this.menuName = builder.menuName;
		this.backgroundColor = builder.backgroundColor;
		this.options = builder.options;
		this.closeBehavior = builder.closeBehavior;
	}
	
	public String getMenuName() {
		return menuName;
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
	
	
	public static class Builder {
		
		private FactionTools main;
		private String menuName = null;
		private int backgroundColor = 0;
		private int rows = 3;
		private ArrayList<Option> options = new ArrayList<>();
		private CLOSE_BEHAVIOR closeBehavior = CLOSE_BEHAVIOR.CLOSE_TO_GAME;
		
		public Builder(FactionTools main) {
			this.main = main;
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
			this.menuName = menuName;
		}
		
		public void setBackgroundColor(int backgroundColor) {
			if (backgroundColor > 0) {
				throw new IllegalArgumentException("Color of tool cannot be smaller than 0")
			}
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
