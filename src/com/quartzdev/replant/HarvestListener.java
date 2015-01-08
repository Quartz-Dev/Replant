package com.quartzdev.replant;

import java.lang.reflect.Array;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class HarvestListener implements Listener{
	
	private final Config config;
	
	public HarvestListener(Config config) {
		this.config = config;
	}
	Material[] crops = config.getCrops();
	
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		Material material = event.getBlock().getType();
		
	}
	
}
