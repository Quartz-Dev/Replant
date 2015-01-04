package com.quartzdev.replant;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Replant extends JavaPlugin{
	Plugin plugin;
	
	@Override
	public void onEnable() {
		
		Config config = new Config(this.getConfig());
		
		HarvestListener harvestListener = new HarvestListener(config);
		this.getServer().getPluginManager().registerEvents(harvestListener, this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
