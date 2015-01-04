package com.quartzdev.replant;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Replant extends JavaPlugin{
	
	private static Logger log = Bukkit.getLogger();
	private final static String PREFIX = "[Replant] ";
	
	@Override
	public void onEnable() {
		
		Config config = new Config(this.getConfig());
		
		HarvestListener harvestListener = new HarvestListener(config);
		
		if(config.enabled()) {
			this.getServer().getPluginManager().registerEvents(harvestListener, this);
		}else{
			log("Replant is disabling because it is disabled in the config.");
		}
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void log(Level level, String... messages) {
		for(String message: messages){
			log.log(level, PREFIX + message);
		}
	}
	
	public static void log(String... messages){
		log(Level.INFO, messages);
	}
	
	
}
