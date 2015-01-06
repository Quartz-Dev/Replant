package com.quartzdev.replant;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class Config {
	
	private Configuration config;
	private boolean immatureCrops;
	private boolean enabled;
	private List<Material> crops;
	private boolean userDefault;
	
	public Config(Configuration config) {
		this.config = config;
		immatureCrops = config.getBoolean("immature-crops");
		enabled = config.getBoolean("enabled");
		userDefault = config.getBoolean("default");
		
		List<String> cropsString = config.getStringList("crops");
		List<Material> crops = new ArrayList<Material>();
		for (String s : cropsString) {
			crops.add(Material.valueOf(s));
		}
		this.crops = crops;
		
	}
	
	public boolean usingImmatureCrops() {
		return immatureCrops;
	}
	
	public boolean enabled() {
		return enabled;
	}
	
	public List<Material> getCrops() {
		return crops;
	}
	
	public boolean getDefault() {
		return userDefault;
	}
	
	public void setDefault(boolean userDefault) {
		this.userDefault = userDefault;
		config.set("default", userDefault);
	}
	
}
