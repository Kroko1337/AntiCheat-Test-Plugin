package de.krokoyt.actest.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.krokoyt.actest.Main;

public class sendmessage implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if(s instanceof Player) {
		
		if(args.length > 1) {
			Player p = Bukkit.getPlayer(args[0]);
			String msg = "";
			for(int i = 1; i < args.length; i++) {
				msg += args[i] + " ";
			}
		p.sendMessage("§7[§3VL§7] " + msg);
		}
		}
		return false;
	}


}
