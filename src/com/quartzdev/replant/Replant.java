package com.quartzdev.replant;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public class Replant extends JavaPlugin {
	
	private static Logger log = Bukkit.getLogger();
	private final static String CONSOLE_PREFIX = "[Replant] ";
	
	@Override
	public void onEnable() {
		
		this.saveDefaultConfig();
		
		Config config = new Config(this.getConfig(), this);
		
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
			log.log(level, CONSOLE_PREFIX + message);
		}
	}
	
	public static void log(String... messages) {
		log(Level.INFO, messages);
	}
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		
		// WorldGuard may not be loaded
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null; // Maybe you want throw an exception instead
		}
		
		return (WorldGuardPlugin) plugin;
	}
	
}
