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
	
	public ReplantCommand(Config config, Plugin plugin) {
		this.config = config;
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
		
		ReplantUser user = (sender instanceof Player) ? ReplantUser.getUser(((Player) sender).getUniqueId()) : null;
		
		if (args.length == 0) {
			String enabled = (user.isEnabled()) ? "enabled" : "disabled";
			sender.sendMessage("Replanting is currently " + enabled + ".");
		}
		
		if (args.length > 0) {
			switch (args[0].toLowerCase()) {
				case "on":
				case "enable":
					if (user != null) {
						user.setEnabled(true);
						sender.sendMessage("Replanting is now enabled!");
					} else {
						sender.sendMessage("You must be a player!");
					}
					break;
				case "off":
				case "disable":
					if (user != null) {
						user.setEnabled(false);
						sender.sendMessage("Replanting is now disabled.");
					} else {
						sender.sendMessage("You must be a player!");
					}
					break;
				case "v":
				case "version":
				case "info":
					sender.sendMessage("Replant v" + plugin.getDescription().getVersion() + " by QuartzDev");
					break;
				default:
					sender.sendMessage("Replant automatically replants crops when broken.");
					if (user != null) {
						if (user.isEnabled()) {
							sender.sendMessage("Type " + ChatColor.GRAY + "/replant off" + ChatColor.WHITE + " to disable.");
						} else {
							sender.sendMessage("Type " + ChatColor.GRAY + "/replant on" + ChatColor.WHITE + " to enable.");
						}
					}
					break;
			}
		}
		
		return true;
	}
}
