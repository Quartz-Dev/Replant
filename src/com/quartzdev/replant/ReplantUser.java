package com.quartzdev.replant;

import java.io.File;
import java.util.MissingResourceException;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplantUser {
	
	private static final File USER_FILE = new File("plugins" + File.separator + "Replant" + File.separator + "users.txt");
	
	private static transient boolean userDefault;
	private boolean enabled;
	private UUID id;
	
	public ReplantUser(UUID id, boolean enabled) {
		this.id = id;
		this.enabled = enabled;
	}
	
	static void onEnable(boolean userDefault) {
		ReplantUser.userDefault = userDefault;
	}
	
	boolean isEnabled() {
		return enabled;
	}
	
	void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	UUID getUUID() {
		return id;
	}
	
	static void setEnabled(CommandSender sender, boolean enabled) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This command can only be used by players");
			return;
		}
		UUID id = ((Player) sender).getUniqueId();
		ReplantUser user = getUser(id);
		
		user.setEnabled(enabled);
		
		return;
	}
	
	static ReplantUser getUser(UUID id) {
		FileManager fMan = new FileManager(USER_FILE);
		
		try {
			boolean b = fMan.getBoolean(id);
			return new ReplantUser(id, b);
		} catch (MissingResourceException ex) {
			return new ReplantUser(id, userDefault);
		}
		
	}
}
