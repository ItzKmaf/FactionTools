package com.kmaf.TrenchTools.Commands;

import com.kmaf.TrenchTools.Command;
import com.kmaf.TrenchTools.CommandHandler;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.command.CommandSender;

public class C_Help implements Command {
	
	private VersionProvider provider;
	private FactionTools main;
	private CommandHandler handler;
	
	
	public C_Help(FactionTools main, VersionProvider provider, CommandHandler handler) {
		this.provider = provider;
		this.main = main;
		this.handler = handler;
	}
	
	@Override
	public String getName() {
		return "Help";
	}
	
	@Override
	public String getDescription() {
		return "Displays a Help Menu";
	}
	
	@Override
	public String getPermission() {
		return "help";
	}
	
	@Override
	public boolean executableByConsole() {
		return true;
	}
	
	@Override
	public void execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
		commandSender.sendMessage("Help");
	}
}
