package com.quartzdev.replant;

import static com.quartzdev.replant.Messages.msg;

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
	
	protected static void onEnable(boolean userDefault) {
		ReplantUser.userDefault = userDefault;
	}
	
	protected boolean isEnabled() {
		return enabled;
	}
	
	protected void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	protected UUID getUUID() {
		return id;
	}
	
	protected static void setEnabled(CommandSender sender, boolean enabled) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(msg("playerOnly"));
			return;
		}
		UUID id = ((Player) sender).getUniqueId();
		ReplantUser user = getUser(id);
		
		user.setEnabled(enabled);
		if (enabled) {
			sender.sendMessage(msg("nowEnabled"));
		} else {
			sender.sendMessage(msg("nowDisabled"));
		}
		return;
	}
	
	private static ReplantUser getUser(UUID id) {
		FileManager fMan = new FileManager(USER_FILE);
		
		try {
			boolean b = fMan.getBoolean(id);
			return new ReplantUser(id, b);
		} catch (MissingResourceException ex) {
			return new ReplantUser(id, userDefault);
		}
		
	}
}
