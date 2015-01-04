package com.quartzdev.replant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class Config {
	
	public Configuration config;
	
	public Config(Configuration config){
		this.config = config;
	}
	
	public boolean usingImmatureCrops() {
		return config.getBoolean("immature-crops");
	}
	
	public boolean enabled() {
		return config.getBoolean("enabled");
	}
	
	public boolean isEverywhere() {
		return config.getBoolean("everywhere");
	}
	
	public Material[] getCrops() {
		List<String> cropsString = config.getStringList("crops");
		List<Material> crops = new ArrayList<Material>();
		for(String s : cropsString) {
			crops.add(Material.valueOf(s));
		}
		Material[] m = new Material[crops.size()];
		m = crops.toArray(m);
		return m;
	}
	
	
	public boolean worldGuardIntegration() {
		return config.getBoolean("world-guard-integration");
	}
	
	public String[] getWhitelistedRegions() {
		List<String> whitelisted = config.getStringList("whitelisted-regions");
		String[] s = new String[whitelisted.size()];
		s = whitelisted.toArray(s);
		return s;
	}
	
	public String[] getBlacklistedRegions() {
		List<String> blacklisted = config.getStringList("whitelisted-regions");
		String[] s = new String[blacklisted.size()];
		s = blacklisted.toArray(s);
		return s;
	}

}
