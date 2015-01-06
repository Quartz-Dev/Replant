package com.quartzdev.replant;

import static com.quartzdev.replant.Messages.msg;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplantUser {
	
	private static final File USER_FILE = new File("plugins" + File.separator + "Replant" + File.separator + "players.txt");
	
	boolean enabled;

	public ReplantUser(boolean enabled) {
		this.enabled = enabled;
	}

	protected boolean isEnabled() {
		return enabled;
	}
	
	protected void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	protected static void setEnabled(CommandSender sender, boolean enabled) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(msg("playerOnly"));
			return;
		}
		UUID id = ((Player)sender).getUniqueId();
		ReplantUser user = getUser(id);
		
		user.setEnabled(enabled);
		return;
	}
	
	private static ReplantUser getUser(UUID id) {
		USER_FILE.getParentFile().mkdirs();
		if(!USER_FILE.exists()){
			try {
				USER_FILE.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return null; // TODO
	}
	

	
	

}
