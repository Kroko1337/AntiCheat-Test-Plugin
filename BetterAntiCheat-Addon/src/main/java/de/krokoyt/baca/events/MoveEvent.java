package de.krokoyt.baca.events;

import de.liquiddev.betteranticheat.main.BAC;
import de.liquiddev.betteranticheat.main.BACAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        BAC.g.put(player, player.hasPermission("betteranticheat.bypass"));
    }
}
