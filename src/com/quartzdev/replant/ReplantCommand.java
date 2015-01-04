package com.quartzdev.replant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static com.quartzdev.replant.Messages.msg;


public class ReplantCommand implements CommandExecutor{

	Config config;
	
	public ReplantCommand(Config config) {
		this.config = config;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(args.length == 0){
			msg("hi");
		}
		
		return false;
	}

}
