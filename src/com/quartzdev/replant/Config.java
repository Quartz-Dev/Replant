package com.quartzdev.replant;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Config {
	
	private Configuration config;
	private boolean immatureCrops;
	private long delay;
	private HashMap<Material, Material> crops;
	private boolean userDefault;
	private Replant mainClass;
	private List<String> forcedRegions;
	
	public Config(Configuration config, Replant mainClass) {
		Bukkit.broadcastMessage("Config loaded!");
		
		this.mainClass = mainClass;
		this.config = config;
		immatureCrops = config.getBoolean("immature-crops");
		userDefault = config.getBoolean("default");
		delay = config.getLong("replace-delay");
		forcedRegions = config.getStringList("forced-regions");
		
		List<String> cropsString = config.getStringList("crops");
		HashMap<Material, Material> crops = new HashMap<>();
		for (String s : cropsString) {
			if (s.contains("=>")) {
				Material material1 = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_").split("=>", 2)[0]);
				Material material2 = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_").split("=>", 2)[1]);
				crops.put(material1, material2);
			} else {
				Material material = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_"));
				Bukkit.broadcastMessage("Material Added: " + material);
				crops.put(material, material);
			}
		}
		this.crops = crops;
		
	}
	
	public boolean usingImmatureCrops() {
		return immatureCrops;
	}
	
	public HashMap<Material, Material> getCrops() {
		return crops;
	}
	
	public Material getReplacementCrop(Material m) {
		return crops.get(m);
	}
	
	public boolean getDefault() {
		return userDefault;
	}
	
	public long getDelay() {
		return delay;
	}
	
	public void setDefault(boolean userDefault) {
		this.userDefault = userDefault;
		config.set("default", userDefault);
	}
	
	public List<String> getForcedRegions() {
		return forcedRegions;
	}
	
	public boolean isReplantForced(Location loc) {
		
		WorldGuardPlugin wgp = mainClass.getWorldGuard();
		
		if (wgp == null) {
			return false;
		}
		
		ApplicableRegionSet regions = wgp.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
		
		List<String> forcedRegions = getForcedRegions();
		for (ProtectedRegion r : regions) {
			if (forcedRegions.contains(r.getId())) {
				return true;
			}
		}
		return false;
	}
	
}
