package com.kmaf.TrenchTools.Tools.TrenchTools;

import com.kmaf.TrenchTools.Config.Config;
import com.kmaf.TrenchTools.Config.InvalidConfiguration;
import com.kmaf.TrenchTools.Config.InvalidConfigurationSection;
import com.kmaf.TrenchTools.Config.InvalidConfigurationValue;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;

public class TrenchToolManager {
	
	private final FactionTools main;
	private final VersionProvider provider;
	
	private final ArrayList<TrenchTool> trenchTools = new ArrayList<>();
	
	public TrenchToolManager(FactionTools main, VersionProvider provider) {
		this.main = main;
		this.provider = provider;
	}
	
	/**
	 *
	 * @return A String array including the config names of all tools
	 */
	private String[] getTrenchToolNames() {
		String[] trenchToolNames = new String[trenchTools.size() -1];
		int count = 0;
		for (TrenchTool tool: trenchTools) {
			trenchToolNames[count] = tool.toString();
			count ++;
		}
		return trenchToolNames;
	}
	
	public void generateToolsFromConfig(Config trenchConfig) throws InvalidConfiguration {
		if (!trenchConfig.getConfig().isConfigurationSection("tools")) {
			throw new InvalidConfigurationSection(trenchConfig, "tools", "The Configuration Section is Malformed or does not exist.");
		}
		ConfigurationSection cs = trenchConfig.getConfig().getConfigurationSection("tools");
		for (String key: cs.getKeys(false)) {
			ConfigurationSection child = cs.getConfigurationSection(key);
			
			// Checking if DisplayName is in the section and also if it is valid.
			if (child == null) {
				throw new InvalidConfigurationValue(trenchConfig, cs.getCurrentPath() + "." + key, "Error, Configuration Section does not exist");
			}
			if (!child.isString("displayName")) {
				throw new InvalidConfigurationValue(trenchConfig, child.getCurrentPath() + ".displayName", "displayName value must be a string (Is it surrounded by \"\"?)");
			}
			String DisplayName = child.getString("displayName");
			System.out.println(DisplayName);
			if (DisplayName == null || DisplayName.trim().equals("")) {
				throw new InvalidConfigurationValue(trenchConfig, child.getCurrentPath() + ".displayName", "displayName Value is missing. (name: \"NameOfTrenchTool\")");
			}
			
			// Checking if diameter is in the section and also if it is valid.
			if (!child.isInt("diameter")) {
				throw new InvalidConfigurationValue(trenchConfig, child.getCurrentPath() + ".diameter", "Diameter value must be an Integer (Not decimal or surrounded by \"\")");
			}
			int diameter = child.getInt("diameter");
			if (diameter <= 0 || diameter > 50) {
				throw new InvalidConfigurationValue(trenchConfig, child.getCurrentPath() + ".diameter", "Diameter value is missing, below one or above 50");
			}
			if (diameter % 2 != 1) {
				throw new InvalidConfigurationValue(trenchConfig, child.getCurrentPath() + ".diameter", "Diameter value must be odd");
			}
			
			// Checking if the section name is valid.
			if (!key.replace(" ", "").equals(key)) {
				throw new InvalidConfigurationSection(trenchConfig, cs.getCurrentPath() + "." + key, "Section name cannot include a space");
			}
			
			TrenchTool.Builder builder = new TrenchTool.Builder();
			builder.setDisplayName(DisplayName);
			builder.setDiameter(diameter);
			builder.setConfigName(key);
			trenchTools.add(builder.build());
		}
	}
	
}
