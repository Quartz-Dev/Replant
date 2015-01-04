package com.quartzdev.replant;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class HarvestListener implements Listener{
	
	private final Config config;
	
	public HarvestListener(Config config) {
		this.config = config;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	
}
