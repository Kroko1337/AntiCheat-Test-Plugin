package de.krokoyt.actest.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.krokoyt.actest.Main;

public class heal implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if(s instanceof Player) {
		Player p = (Player)s;
		p.setHealth(20D);
		p.sendMessage(Main.prefix + "§7Du wurdest geheilt.");
		}
		return false;
	}

}
