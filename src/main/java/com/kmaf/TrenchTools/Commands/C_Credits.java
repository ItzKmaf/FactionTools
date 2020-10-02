package com.kmaf.TrenchTools.Commands;

import com.kmaf.TrenchTools.Command;
import com.kmaf.TrenchTools.CommandHandler;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.command.CommandSender;

public class C_Credits implements Command {
	
	private VersionProvider provider;
	private FactionTools main;
	private CommandHandler handler;
	
	
	public C_Credits(FactionTools main, VersionProvider provider, CommandHandler handler) {
		this.provider = provider;
		this.main = main;
		this.handler = handler;
	}
	
	@Override
	public String getName() {
		return "Credits";
	}
	
	@Override
	public String getDescription() {
		return "Displays the credits for the plugin to the user";
	}
	
	@Override
	public String getPermission() {
		return null;
	}
	
	@Override
	public boolean executableByConsole() {
		return true;
	}
	
	@Override
	public void execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
	
	}
}
