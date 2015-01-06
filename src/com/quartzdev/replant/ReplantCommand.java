package com.quartzdev.replant;

import static com.quartzdev.replant.Messages.msg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
		
		if (!config.enabled()) {
			if (sender.hasPermission("replant.admin")) {
				sender.sendMessage(msg("pluginDisabledOp"));
			} else {
				sender.sendMessage(msg("pluginDisabled"));
			}
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(msg("version", plugin.getDescription().getVersion()));
			sender.sendMessage(msg("on")); // TODO Check if the user has it on
											// or off.
		}
		
		if (args.length == 1) {
			switch (args[0].toLowerCase()) {
				case "on":
					ReplantUser.setEnabled(sender, true);
					break;
				case "off":
					ReplantUser.setEnabled(sender, false);
					break;
			
			}
		}
		
		return false;
	}
	
}
