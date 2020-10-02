package com.kmaf.TrenchTools.Commands;

import com.kmaf.TrenchTools.Command;
import com.kmaf.TrenchTools.CommandHandler;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.GUI.Menu;
import com.kmaf.TrenchTools.VersionProvider;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
		Menu.Builder menuBuilder = new Menu.Builder(main, provider);
		menuBuilder.setMenuName("&8&l[&6Faction Tools Admin GUI&8&l]");
		provider.openMenu(menuBuilder.build(), (Player) commandSender);
	}
}
