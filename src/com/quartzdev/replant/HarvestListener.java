package com.quartzdev.replant;

import java.util.Collection;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.plugin.Plugin;

public class HarvestListener implements Listener {
	
	private final Plugin plugin;
	private final Config config;
	
	public HarvestListener(Plugin plugin, Config config) {
		this.plugin = plugin;
		this.config = config;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		Material brokenMaterial = event.getBlock().getType();
		
		ReplantUser user = ReplantUser.getUser(event.getPlayer().getUniqueId());
		
		if (user != null) {
			if (!user.isEnabled()) {
				return;
			}
		}
		
		Material replMaterial = config.getReplacementCrop(brokenMaterial);
		if (replMaterial.equals(null)) {
			return;
		} else {
			
			Crops c = new Crops(brokenMaterial);
			if (!config.usingImmatureCrops()) {
				if (!c.getState().equals(CropState.RIPE)) {
					return;
				}
			}
			
			Collection<ItemStack> drops = event.getBlock().getDrops();
			if (drops.contains(replMaterial)) {
				event.getBlock().getDrops().remove(replMaterial);
				Replacer replacer = new Replacer(event.getBlock(), replMaterial);
				long delay = config.getDelay();
				plugin.getServer().getScheduler().runTaskLater(plugin, replacer, delay);
			}
		}
		
	}
}
