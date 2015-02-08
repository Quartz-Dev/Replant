package com.quartzdev.replant;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class HarvestListener implements Listener {
	
	private final Plugin plugin;
	private final Config config;
	
	public HarvestListener(Plugin plugin, Config config) {
		this.plugin = plugin;
		this.config = config;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		
		Material brokenMaterial = event.getBlock().getType();
		ReplantUser user = ReplantUser.getUser(event.getPlayer().getUniqueId());
		
		if (user != null) {
			if (!user.isEnabled() && !config.isReplantForced(event.getBlock().getLocation())) {
				return;
			}
		}
		
		Material replMaterial = config.getReplacementCrop(brokenMaterial);
		if (replMaterial == null) {
			return;
		} else {
			
			try {
				byte b = event.getBlock().getData();
				
				if (!config.usingImmatureCrops()) {
					if (b < 7) {
						if (!(brokenMaterial.equals(Material.NETHER_WARTS) && b == 3)) {
							return;
						}
					}
				}
				
			} catch (ClassCastException e) {
				// Do nothing.
			}
			
			Collection<ItemStack> drops = event.getBlock().getDrops();
			ItemStack replStack = new ItemStack(replMaterial);
			if (drops.contains(replStack)) {
				event.getBlock().getDrops().remove(replMaterial);
			}
			Replacer replacer = new Replacer(event.getBlock(), replMaterial);
			long delay = config.getDelay();
			plugin.getServer().getScheduler().runTaskLater(plugin, replacer, delay);
			
		}
		
	}
}
