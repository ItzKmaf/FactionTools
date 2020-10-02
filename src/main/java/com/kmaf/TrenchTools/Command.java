package com.kmaf.TrenchTools;

import org.bukkit.command.CommandSender;

public interface Command {
	
	String getName();
	
	String getDescription();
	
	String getPermission();
	
	boolean executableByConsole();
	
	void execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args);
	
	//TODO
	//  Add a tab complete thing.
}
