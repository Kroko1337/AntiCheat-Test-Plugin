package de.krokoyt.actest.cmds;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.krokoyt.actest.Main;

public class addperms implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if(s instanceof Player) {
		Player p = (Player)s;
			if(args.length == 2) {
				String pluginName = args[0];
				String perms = args[1];
				if(p.hasPermission("actest.addperms")) {
					if(!Main.instance.pcfg.contains(pluginName)){
						Main.instance.pcfg.set(pluginName, perms);
						
						try {
							Main.instance.pcfg.save(Main.instance.perms);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						p.sendMessage(Main.prefix + "§aPermission §e" + perms + " §awurde zu §e" + pluginName + " §ahinzugefügt!");
					}else {
						p.sendMessage(Main.prefix + "§cDas Plugin hat bereits eine Permission!");
					}
				}
			}else {
				p.sendMessage(Main.prefix + "§cWrong Usage: §7/addperms <Plugin Name> <Permission>");
			}
		}
		return false;
	}


}
