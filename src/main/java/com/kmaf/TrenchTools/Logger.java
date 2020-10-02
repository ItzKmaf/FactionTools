package com.kmaf.TrenchTools;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Logger {
	
	private ConsoleCommandSender console;
	
	public Logger(ConsoleCommandSender console) {
		this.console = console;
	}
	
	public void logToConsole(String message) {
		message = message.replace("{plugin-name}", "&8[&6Faction-Tools&8]&7");
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
}
