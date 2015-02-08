package com.quartzdev.replant;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;

public class Config {
	
	private Configuration config;
	private boolean immatureCrops;
	private long delay;
	private HashMap<Material, Material> crops;
	private boolean userDefault;
	
	public Config(Configuration config) {
		this.config = config;
		immatureCrops = config.getBoolean("immature-crops");
		userDefault = config.getBoolean("default");
		delay = config.getLong("replace-delay");
		
		List<String> cropsString = config.getStringList("crops");
		HashMap<Material, Material> crops = new HashMap<>();
		for (String s : cropsString) {
			if (s.contains("=>")) {
				Material material1 = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_").split("=>", 1)[0]);
				Material material2 = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_").split("=>", 1)[1]);
				crops.put(material1, material2);
			} else {
				Material material = Material.getMaterial(s.toUpperCase().replaceAll(" ", "_"));
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
	
}
