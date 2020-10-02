package com.kmaf.TrenchTools.Config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


public class Config {
	private File ConfigFile;
	private FileConfiguration ConfFile;
	private JavaPlugin plugin;
	private String filename;
	
	public Config(JavaPlugin plugin, String filename) {
		this.filename = filename;
		this.plugin = plugin;
		this.ConfigFile = new File(plugin.getDataFolder(), filename);
		if (!this.ConfigFile.exists()) {
			this.ConfigFile.getParentFile().mkdirs();
			plugin.saveResource(filename, false);
		}
		
		this.ConfFile = new YamlConfiguration();
		
		try {
			this.ConfFile.load(this.ConfigFile);
		} catch (InvalidConfigurationException | IOException var4) {
			var4.printStackTrace();
		}
		
	}
	
	public FileConfiguration getConfig() {
		return this.ConfFile;
	}
	
	public void createConfig(String file) {
		this.ConfigFile = new File(this.plugin.getDataFolder(), this.filename);
		if (!this.ConfigFile.exists()) {
			this.ConfigFile.getParentFile().mkdirs();
			this.plugin.saveResource(this.filename, false);
		}
		
		this.ConfFile = new YamlConfiguration();
		
		try {
			this.ConfFile.load(this.ConfigFile);
		} catch (InvalidConfigurationException | IOException var3) {
			var3.printStackTrace();
		}
		
	}
	
	public void saveConfig() {
		try {
			this.ConfFile.save(this.ConfigFile);
		} catch (Exception var2) {
			var2.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		return filename.replace(".yml", "");
	}
}
