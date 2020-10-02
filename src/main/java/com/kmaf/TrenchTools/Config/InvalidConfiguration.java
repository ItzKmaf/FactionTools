package com.kmaf.TrenchTools.Config;

public class InvalidConfiguration extends Exception {
	
	protected final Config config;
	
	public InvalidConfiguration(Config config) {
		this.config = config;
	}
	
	@Override
	public String toString() {
		return "&7[&6Faction-Tools&7] &c&lWARNING\n&cAn error occurred while loading the Configuration File &a" + config;
	}
}
