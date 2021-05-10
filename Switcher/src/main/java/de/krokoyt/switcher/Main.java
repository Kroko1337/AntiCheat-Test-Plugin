package de.krokoyt.switcher;

import de.krokoyt.switcher.commands.AddAntiCheat;
import de.krokoyt.switcher.commands.AntiCheat;
import de.krokoyt.switcher.events.InventoryClick;
import de.krokoyt.switcher.events.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public static Main instance;

    public final File directory;

    public final File permissionFile;
    public final FileConfiguration permissionConfiguration;

    public final File playerFile;
    public final FileConfiguration playerConfiguration;

    public static final HashMap<Player, String> ANTICHEAT = new HashMap<>();

    public Main() {
        this.directory = getDataFolder();
        this.playerFile = new File(directory, "player.cfg");
        this.playerConfiguration = YamlConfiguration.loadConfiguration(playerFile);
        this.permissionFile = new File(directory, "permissions.cfg");
        this.permissionConfiguration = YamlConfiguration.loadConfiguration(permissionFile);
    }

    @Override
    public void onEnable() {
        instance = this;

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClick(), this);
        pluginManager.registerEvents(new JoinEvent(), this);

        getCommand("anticheat").setExecutor(new AntiCheat());
        getCommand("addanticheat").setExecutor(new AddAntiCheat());
        saveConfig();

        try {
            if(this.permissionFile.exists()) {
                this.permissionFile.createNewFile();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        super.onEnable();
    }

    public static String getAntiCheat(Player player) {
        return ANTICHEAT.getOrDefault(player, null);
    }

    public Main getInstance() {
        return instance;
    }
}
