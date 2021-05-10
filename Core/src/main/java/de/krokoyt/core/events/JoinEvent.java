package de.krokoyt.core.events;

import de.krokoyt.core.Core;
import de.krokoyt.core.data.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        if (!Core.USERS.containsKey(player.getUniqueId()))
            Core.USERS.put(uuid, new User(player.getUniqueId()));
        else
            Core.USERS.get(uuid).player = Bukkit.getPlayer(uuid);
    }

}
