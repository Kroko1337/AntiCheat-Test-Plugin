package de.krokoyt.switcher.events;

import de.krokoyt.switcher.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        final Player player = event.getPlayer();
        if (!Main.instance.playerConfiguration.contains(String.valueOf(player.getUniqueId().getMostSignificantBits()))) {
            //TODO: Warp System integration
            Main.instance.playerConfiguration.set(player.getUniqueId().getMostSignificantBits() + ".anticheat", "Vanilla");
            Main.instance.playerConfiguration.save(Main.instance.playerFile);
        }

        Main.ANTICHEAT.put(player, Main.instance.playerConfiguration.getString(player.getUniqueId().getMostSignificantBits() + ".anticheat"));

        final LuckPerms luckPerms = LuckPermsProvider.get();
        final User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (Main.instance.permissionConfiguration.contains(plugin.getName()))
                    user.getData(DataType.NORMAL).add(PermissionNode.builder(Main.instance.permissionConfiguration.getString(plugin.getName() + ".permission")).build());
            }
            if (!Main.ANTICHEAT.get(player).equalsIgnoreCase("Vanilla")) {
                final Plugin plugin = Bukkit.getPluginManager().getPlugin(Main.ANTICHEAT.get(player));
                user.getData(DataType.NORMAL).remove(PermissionNode.builder(Main.instance.permissionConfiguration.getString(plugin.getName() + ".permission")).build());
            }
            luckPerms.getUserManager().saveUser(user);
        }
    }
}
