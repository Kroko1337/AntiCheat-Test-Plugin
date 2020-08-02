package de.krokoyt.actest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.elikill58.negativity.spigot.listeners.PlayerCheatAlertEvent;
import com.elikill58.negativity.spigot.listeners.PlayerCheatBypassEvent;
import com.elikill58.negativity.spigot.listeners.PlayerCheatEvent;

public class NegativityEvents implements Listener{
	
	@EventHandler
	public void onFlag(PlayerCheatAlertEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(Main.flag + "§7Detected: " + e.getCheat().getName() + " Reliability: " + e.getReliability());
	
	}

}
