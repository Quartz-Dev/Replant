package com.quartzdev.replant;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Replant extends JavaPlugin{
	Plugin plugin;
	
	@Override
	public void onEnable() {
		HarvestListener harvestListener = new HarvestListener();
		this.getServer().getPluginManager().registerEvents(harvestListener, this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
