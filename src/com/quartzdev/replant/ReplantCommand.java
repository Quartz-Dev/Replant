package com.quartzdev.replant;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ReplantCommand implements CommandExecutor {
	
	Config config;
	Plugin plugin;
	
	private static final String PREFIX = ChatColor.RED + "[" + ChatColor.AQUA + "Replant" + ChatColor.RED + "] " + ChatColor.GRAY;
	private static final ChatColor RESET_COLOR = ChatColor.GRAY;
	private static final ChatColor SECONDARY_COLOR = ChatColor.BLUE;
	private static final ChatColor ALERT_COLOR = ChatColor.RED;
	
	public ReplantCommand(Config config, Plugin plugin) {
		this.config = config;
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
		
		ReplantUser user = (sender instanceof Player) ? ReplantUser.getUser(((Player) sender).getUniqueId()) : null;
		
		if (args.length == 0) {
			if (!sender.hasPermission("replant.command.default")) {
				sender.sendMessage(PREFIX + ALERT_COLOR + "You do not have anough permission.");
				return false;
			}
			
			String enabled = (user.isEnabled()) ? "enabled" : "disabled";
			if (config.isReplantForced(((Player) sender).getLocation())) {
				sender.sendMessage(PREFIX + "Replanting is currently " + enabled + ". (Replanting is forced in this region.)");
			} else {
				sender.sendMessage(PREFIX + "Replanting is currently " + enabled + ".");
			}
		}
		
		if (args.length > 0) {
			switch (args[0].toLowerCase()) {
				case "on":
				case "enable":
					if (user != null) {
						if (!sender.hasPermission("replant.command.enable")) {
							sender.sendMessage(PREFIX + ALERT_COLOR + "You do not have anough permission.");
							return false;
						}
						
						user.setEnabled(true);
						if (config.isReplantForced(((Player) sender).getLocation())) {
							sender.sendMessage(PREFIX + "Replanting is now enabled! (Replanting is forced in this region.)");
						} else {
							sender.sendMessage(PREFIX + "Replanting is now enabled!");
						}
					} else {
						sender.sendMessage(PREFIX + "You must be a player!");
					}
					break;
				case "off":
				case "disable":
					if (user != null) {
						if (!sender.hasPermission("replant.command.disable")) {
							sender.sendMessage(PREFIX + ALERT_COLOR + "You do not have anough permission.");
							return false;
						}
						
						user.setEnabled(false);
						if (config.isReplantForced(((Player) sender).getLocation())) {
							sender.sendMessage(PREFIX + "Replanting is now disabled! (Replanting is forced in this region.)");
						} else {
							sender.sendMessage(PREFIX + "Replanting is now disabled!");
						}
					} else {
						sender.sendMessage(PREFIX + "You must be a player!");
					}
					break;
				case "v":
				case "version":
				case "info":
					if (!sender.hasPermission("replant.command.info")) {
						sender.sendMessage(PREFIX + ALERT_COLOR + "You do not have anough permission.");
						return false;
					}
					
					sender.sendMessage(PREFIX + "Replant v" + plugin.getDescription().getVersion() + " by QuartzDev");
					sender.sendMessage(PREFIX + "Replant automatically replants crops when broken.");
					if (user != null) {
						if (user.isEnabled()) {
							sender.sendMessage(PREFIX + "Type " + SECONDARY_COLOR + "/replant off" + RESET_COLOR + " to disable.");
						} else {
							sender.sendMessage(PREFIX + "Type " + SECONDARY_COLOR + "/replant on" + RESET_COLOR + " to enable.");
						}
					}
					break;
				default:
					if (!sender.hasPermission("replant.command.unknown")) {
						sender.sendMessage(PREFIX + ALERT_COLOR + "You do not have anough permission.");
						return false;
					}
					
					sender.sendMessage(PREFIX + ALERT_COLOR + "Unknown Command");
					
					break;
			}
		}
		
		return true;
	}
}
