package de.krokoyt.core;

import de.krokoyt.core.data.User;
import de.krokoyt.core.events.JoinEvent;
import de.krokoyt.core.events.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Core extends JavaPlugin {

    public static final HashMap<UUID, User> USERS = new HashMap<>();
    public static final String PREFIX = "§aCore §8>> §7";

    @Override
    public void onEnable() {
        for (Player player : Bukkit.getOnlinePlayers())
            player.kickPlayer("Reconnect please!");

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new QuitEvent(), this);
        super.onEnable();
    }

    public static User getUser(Player player) {
        return Core.USERS.get(player.getUniqueId());
    }
}
