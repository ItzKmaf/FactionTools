package com.kmaf.TrenchTools.Config;

import org.bukkit.configuration.ConfigurationSection;

public class InvalidConfigurationSection extends InvalidConfiguration {
	
	protected final String sectionPath;
	protected final String reason;
	
	public InvalidConfigurationSection(Config config, String sectionPath, String reason) {
		super(config);
		this.sectionPath = sectionPath;
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		String previous = super.toString();
		previous += "\n&cThe Configuration Section with the path &a" + sectionPath + " &cis Malformed.";
		previous += "\n&8Reason: &c" + reason;
		return previous;
	}
}
