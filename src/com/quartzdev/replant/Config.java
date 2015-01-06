package com.quartzdev.replant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class Config {
	
	private boolean immatureCrops;
	private boolean enabled;
	private boolean everywhere;
	private List<Material> crops;
	private boolean worldGuard;
	private List<String> whitelisted;
	private List<String> blacklisted;
	
	public Config(Configuration config) {
		immatureCrops = config.getBoolean("immature-crops");
		enabled = config.getBoolean("enabled");
		everywhere = config.getBoolean("everywhere");
		
		List<String> cropsString = config.getStringList("crops");
		List<Material> crops = new ArrayList<Material>();
		for (String s : cropsString) {
			crops.add(Material.valueOf(s));
		}
		this.crops = crops;
		
		this.whitelisted = config.getStringList("whitelisted-regions");
		
		this.blacklisted = config.getStringList("whitelisted-regions");
	}
	
	public boolean usingImmatureCrops() {
		return immatureCrops;
	}
	
	public boolean enabled() {
		return enabled;
	}
	
	public boolean isEverywhere() {
		return everywhere;
	}
	
	public List<Material> getCrops() {
		return crops;
	}
	
	public boolean worldGuardIntegration() {
		return worldGuard;
	}
	
	public List<String> getWhitelistedRegions() {
		return whitelisted;
	}
	
	public List<String> getBlacklistedRegions() {
		return blacklisted;
	}
	
}
