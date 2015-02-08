package com.quartzdev.replant;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Replant extends JavaPlugin {
	
	private static Logger log = Bukkit.getLogger();
	private final static String PREFIX = "[Replant] ";
	
	@Override
	public void onEnable() {
		
		File realConfigFile = new File("plugins" + File.separator + "Replant" + File.separator + "config.yml");
		if (!realConfigFile.exists()) {
			this.getConfig().options().copyDefaults(true);
			try {
				this.getConfig().save(realConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Config config = new Config(this.getConfig());
		
		ReplantUser.onEnable(config.getDefault());
		
		HarvestListener harvestListener = new HarvestListener(this, config);
		ReplantCommand replantCommand = new ReplantCommand(config, this);
		
		this.getServer().getPluginManager().registerEvents(harvestListener, this);
		this.getCommand("replant").setExecutor(replantCommand);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void log(Level level, String... messages) {
		for (String message : messages) {
			log.log(level, PREFIX + message);
		}
	}
	
	public static void log(String... messages) {
		log(Level.INFO, messages);
	}
	
}
