package de.krokoyt.baca;

import de.krokoyt.baca.api.ViolationHandling;
import de.krokoyt.baca.events.MoveEvent;
import de.liquiddev.betteranticheat.main.BAC;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new MoveEvent(), this);
        BAC.getAPIs().add(new ViolationHandling());
        super.onEnable();
    }
}
