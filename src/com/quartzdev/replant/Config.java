package com.quartzdev.replant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class Config {
	
	public Configuration config;
	private boolean immatureCrops;
	private boolean enabled;
	private boolean everywhere;
	private Material[] crops;
	private boolean worldGuard;
	private String[] whitelisted;
	private String[] blacklisted;
	
	public Config(Configuration config){
		this.config = config;
		immatureCrops = config.getBoolean("immature-crops");
		enabled = config.getBoolean("enabled");
		everywhere = config.getBoolean("everywhere");
		
		List<String> cropsString = config.getStringList("crops");
		List<Material> crops = new ArrayList<Material>();
		for(String s : cropsString) {
			crops.add(Material.valueOf(s));
		}
		Material[] m = new Material[crops.size()];
		m = crops.toArray(m);
		this.crops = m;
		
		List<String> whitelisted = config.getStringList("whitelisted-regions");
		String[] white = new String[whitelisted.size()];
		white = whitelisted.toArray(white);
		this.whitelisted = white;
		
		List<String> blacklisted = config.getStringList("whitelisted-regions");
		String[] black = new String[blacklisted.size()];
		black = blacklisted.toArray(black);
		this.blacklisted = black;
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
	
	public Material[] getCrops() {
		return crops;
	}
	
	
	public boolean worldGuardIntegration() {
		return worldGuard;
	}
	
	public String[] getWhitelistedRegions() {
		return whitelisted;
	}
	
	public String[] getBlacklistedRegions() {
		return blacklisted;
	}

}
