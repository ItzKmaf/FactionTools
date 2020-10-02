package com.kmaf.TrenchTools;

import com.kmaf.TrenchTools.Config.Config;
import com.kmaf.TrenchTools.Tools.ToolManager;
import com.kmaf.TrenchTools.VersionProviders.Spigot_1_16_1_Snap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FactionTools extends JavaPlugin {
	
	private Logger logger;
	private String serverVersion;
	private VersionProvider versionProvider;
	private Config trenchConfig;
	private ToolManager toolManager;
	
	@Override
	public void onEnable() {
		long startTime = System.currentTimeMillis();
		logger = new Logger(Bukkit.getConsoleSender());
		printLoadMessage(getDescription());
		serverVersion = Bukkit.getBukkitVersion();
		versionProvider = getVersionProvider(serverVersion);
		toolManager = new ToolManager(this, versionProvider);
		toolManager.loadConfigs();
		toolManager.generateTools();
		//Register Commands.
		getCommand("factiontools").setExecutor(new CommandHandler(this, versionProvider));
		long loadTime = System.currentTimeMillis() - startTime;
		long sec = loadTime / 1000L;
		long millis = loadTime % 1000L;
		logger.logToConsole("{plugin-name}: &aStartup completed in &c" + sec + " Seconds, " + millis + " Milli-seconds.");
	}
	
	@Override
	public void onDisable() {
		printUnloadMessage(getDescription());
	}
	
	
	private VersionProvider getVersionProvider(String serverVersion) {
		if (serverVersion.equals("1.16.1-R0.1-SNAPSHOT")) {
			logger.logToConsole("{plugin-name}: Found Supported version provider: &a1.16.1-R0.1-SNAPSHOT");
			return new Spigot_1_16_1_Snap();
		}

		//Return Latest supported version
		logger.logToConsole("{plugin-name}: &c&lWARNING!! Could not find a supported version provider\nThe plugin may not behave as intended or may not function at all. Use at your own use and contact the developer asap.");
		logger.logToConsole("{plugin-name}: For reference you are using Bukkit Version &c" + Bukkit.getBukkitVersion());
		return new Spigot_1_16_1_Snap();
	}
	
	private void printLoadMessage(PluginDescriptionFile pdf) {
		logger.logToConsole("&7<---========={plugin-name}&7=========--->");
		logger.logToConsole("&aLoading " + pdf.getName());
		logger.logToConsole("&7Version: " + pdf.getVersion());
		logger.logToConsole("&7Author: " + pdf.getAuthors());
		logger.logToConsole("&7<---========={plugin-name}&7=========--->");
		logger.logToConsole("&c&l=========================================================");
		logger.logToConsole("For support join the discord! https://discord.gg/EayPh9M");
		logger.logToConsole("&c&l=========================================================");
	}
	
	private void printUnloadMessage(PluginDescriptionFile pdf) {
		logger.logToConsole("&7<---========={plugin-name}&7=========--->");
		logger.logToConsole("&cUnloading " + pdf.getName());
		logger.logToConsole("&7Version: " + pdf.getVersion());
		logger.logToConsole("&7Author: " + pdf.getAuthors());
		logger.logToConsole("&7<---========={plugin-name}&7=========--->");
		logger.logToConsole("&c&l=========================================================");
		logger.logToConsole("For support join the discord! https://discord.gg/EayPh9M");
		logger.logToConsole("&c&l=========================================================");
	}
	
	public Logger getConsoleLogger() {
		return logger;
	}
}
