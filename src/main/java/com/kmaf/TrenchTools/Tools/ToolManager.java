package com.kmaf.TrenchTools.Tools;

import com.kmaf.TrenchTools.Config.Config;
import com.kmaf.TrenchTools.Config.InvalidConfiguration;
import com.kmaf.TrenchTools.FactionTools;
import com.kmaf.TrenchTools.Tools.TrenchTools.TrenchToolManager;
import com.kmaf.TrenchTools.VersionProvider;

public class ToolManager {
	
	private FactionTools main;
	private VersionProvider provider;
	private TrenchToolManager trenchToolManager;
	private Config trenchToolConfig;
	
	/**
	 * Creates an instance of the Tool Manager.
	 * @param main The Instance of the Main Plugin class
	 * @param provider The Interface that we use to communicate with version dependant methods of the spigot API
	 */
	public ToolManager(FactionTools main, VersionProvider provider) {
		this.main = main;
		this.provider = provider;
		trenchToolManager = new TrenchToolManager(main, provider);
	}
	
	public void loadConfigs() {
		trenchToolConfig = new Config(main,"trench.yml");
	}
	
	public void generateTools() {
		try {
			trenchToolManager.generateToolsFromConfig(trenchToolConfig);
		} catch (InvalidConfiguration e) {
			String[] split = (e + "\n&cBecause of this the plugin may not function correctly.\n&cEither fix the configuration file or contact the Developer if you believe this is incorrect.").toString().split("\n");
			for (String st: split) {
				main.getConsoleLogger().logToConsole(st);
			}
		}
	}
	
	
}
