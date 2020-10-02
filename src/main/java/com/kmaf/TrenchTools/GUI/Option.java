package com.kmaf.TrenchTools.GUI;

import org.bukkit.entity.Player;

public class Option {
	
	public String displayName;
	
	public Option(Builder builder) {
		this.displayName = builder.displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void onClick(Player player) {
	
	}
	
	
	public static class Builder {
		private String configName = null;
		private String displayName = null;
		private int slot = -1;
	}
}
