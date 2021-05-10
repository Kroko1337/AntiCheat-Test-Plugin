package de.krokoyt.core.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    public Player player;

    public User(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }
}
