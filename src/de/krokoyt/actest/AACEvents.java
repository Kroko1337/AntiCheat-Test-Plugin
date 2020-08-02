package de.krokoyt.actest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.konsolas.aac.api.PlayerViolationEvent;

public class AACEvents implements Listener{

	
	@EventHandler
	public void onAACFlag(PlayerViolationEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(Main.flag + "" + e.getMessage());
	}
}
