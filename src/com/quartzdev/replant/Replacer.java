package com.quartzdev.replant;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.CocoaPlant;

public class Replacer implements Runnable {
	
	private Block block;
	private Material toReplace;
	
	public Replacer(Block block, Material toReplace) {
		this.block = block;
		this.toReplace = toReplace;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if (block.getType().equals(Material.AIR)) {
			
			BlockFace placeDir = null;
			
			block.setType(toReplace);
			block.getState().update(true, true);
			block.getRelative(BlockFace.DOWN).getState().update(true);
			
			if (block.getState().getData() instanceof CocoaPlant) {
				BlockFace[] faces = { BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH };
				CocoaPlant cocoa = (CocoaPlant) block.getState().getData();
				
				for (BlockFace face : faces) {
					Block facing = block.getRelative(face);
					if (facing.getType().equals(Material.LOG)) {
						int log = facing.getData() % 4;
						if (log == 3) {
							placeDir = face;
							cocoa.setFacingDirection(placeDir);
							block.setData(cocoa.getData(), true);
							
							break;
						}
					}
				}
				
				if (placeDir == null) {
					block.breakNaturally();
				}
			}
		}
	}
	
}
