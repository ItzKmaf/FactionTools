package com.kmaf.TrenchTools;

import com.kmaf.TrenchTools.Commands.C_Credits;
import com.kmaf.TrenchTools.Commands.C_GUI;
import com.kmaf.TrenchTools.Commands.C_Help;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandHandler implements CommandExecutor {
	
	private ArrayList<Command> commands = new ArrayList<>();
	private C_GUI guiCommand;
	private C_Help helpCommand;
	private C_Credits creditsCommand;
	private FactionTools main;
	public CommandHandler(FactionTools main, VersionProvider versionProvider) {
		this.main = main;
		guiCommand = new C_GUI(main, versionProvider, this);
		commands.add(guiCommand);
		helpCommand = new C_Help(main, versionProvider, this);
		commands.add(creditsCommand);
		creditsCommand = new C_Credits(main, versionProvider, this);
		commands.add(creditsCommand);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {
		if (args.length == 0) {
			// The user has not entered an argument.
			if (commandSender.hasPermission(guiCommand.getPermission())) {
				if (commandSender instanceof Player || guiCommand.executableByConsole()) {
					guiCommand.execute(commandSender, command, label, args);
				} else {
					main.getConsoleLogger().logToConsole("&cError. This command cannot be executed by Console.");
				}
			} else {
				creditsCommand.execute(commandSender, command, label, args);
			}
		}
		return false;
	}
}
