package com.quartzdev.replant;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class HarvestListener implements Listener{
	
	private Config config;
	private Plugin plugin;
	
	public HarvestListener(Config config, Replant replant) {
		this.config = config;
		this.plugin = replant;
	}
	List<Material> crops = config.getCrops();
	
	HashMap<Material, ItemStack> crop_drops = new HashMap<Material, ItemStack>();
	
	public void loadDrops(){
		
	}
	
	public void replant(Block block, Material crop, Collection<Material> drops){
		ItemStack seed;
		seed = crop_drops.get(crop);
		
		if(seed == null){
			return;
		}
		
		if(!drops.contains(seed)){
			return;
		}
		
		drops.remove(seed);
		
		block.setType(crop);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onBlockBreak(BlockBreakEvent event) {
		
		Material material = event.getBlock().getType();
		Collection<ItemStack> drops = event.getBlock().getDrops();
		
		if(!config.enabled()){
			return;
		}
		
		if(!crops.contains(material)){
			return;
		}
		
		
		
	}
	
}
