package com.kmaf.TrenchTools.Commands;

import com.kmaf.TrenchTools.Command;
import com.kmaf.TrenchTools.CommandHandler;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.command.CommandSender;

public class C_GUI implements Command {
	
	private VersionProvider provider;
	private FactionTools main;
	private CommandHandler handler;
	
	
	public C_GUI(FactionTools main, VersionProvider provider, CommandHandler handler) {
		this.provider = provider;
		this.main = main;
		this.handler = handler;
	}
	
	@Override
	public String getName() {
		return "gui";
	}
	
	@Override
	public String getDescription() {
		return "Opens the Admin GUI";
	}
	
	@Override
	public String getPermission() {
		return "FactionTools.admin.gui";
	}
	
	@Override
	public boolean executableByConsole() {
		return false;
	}
	
	@Override
	public void execute(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
		//TODO
		//  Create the GUI for giving tools and then open it.
		//  We will listen for events in a different area i think.
		//  Create the tools before i come back to this.
	}
}
