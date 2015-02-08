package com.quartzdev.replant;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class Replacer implements Runnable {
	
	private Block block;
	private Material toReplace;
	
	public Replacer(Block block, Material toReplace) {
		this.block = block;
		this.toReplace = toReplace;
	}
	
	@Override
	public void run() {
		if (block.getType().equals(Material.AIR)) {
			block.setType(toReplace);
			block.getState().update();
		}
	}
	
}
