package com.quartzdev.replant;

import static com.quartzdev.replant.Messages.msg;

import java.io.File;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplantUser {
	
	private static final File USER_FILE = new File("");
	
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
		return null; // TODO
	}
	

	
	

}
