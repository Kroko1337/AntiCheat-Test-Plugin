package de.krokoyt.switcher.events;

import de.krokoyt.core.Core;
import de.krokoyt.switcher.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.DataType;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.IOException;

public class InventoryClick implements Listener {
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getName().equalsIgnoreCase("§eSelect AntiCheat")) {
            event.setCancelled(true);
            final String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
            final ItemStack itemStack = event.getCurrentItem();
            final Player player = (Player) event.getWhoClicked();
            final LuckPerms luckPerms = LuckPermsProvider.get();
            if (itemStack != null) {
                final User user = luckPerms.getUserManager().getUser(player.getUniqueId());
                if (user != null)
                    switch (itemStack.getData().getData()) {
                        case 14:
                            player.closeInventory();
                            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                                if (Main.instance.permissionConfiguration.contains(plugin.getName()))
                                    user.getData(DataType.NORMAL).add(PermissionNode.builder(Main.instance.permissionConfiguration.getString(plugin.getName() + ".permission")).build());
                            }
                            if(!name.equalsIgnoreCase("Vanilla")) {
                                final Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
                                user.getData(DataType.NORMAL).remove(PermissionNode.builder(Main.instance.permissionConfiguration.getString(plugin.getName() + ".permission")).build());
                            }
                            luckPerms.getUserManager().saveUser(user);
                            Main.instance.playerConfiguration.set(player.getUniqueId().getMostSignificantBits() + ".anticheat", name);
                            try {
                                Main.instance.playerConfiguration.save(Main.instance.playerFile);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            Main.ANTICHEAT.put(player, name);
                            player.sendMessage(Core.PREFIX + "§aAntiCheat changed to §e" + name + " §a!");
                            break;
                        case 5:
                            player.closeInventory();
                            player.sendMessage(Core.PREFIX + "§cYou have already chosen that AntiCheat!");
                            break;
                    }
            }
        }
    }
}
