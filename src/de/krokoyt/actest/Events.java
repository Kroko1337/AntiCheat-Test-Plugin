package de.krokoyt.actest;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import de.krokoyt.actest.cmds.ac;
import me.lucko.luckperms.common.api.LuckPermsApiProvider;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeBuilder;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.metadata.NodeMetadataKey;
import net.luckperms.api.node.types.PermissionNode;

public class Events implements Listener {

	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		if (e.getClickedInventory().getName().equalsIgnoreCase("§eSelect Anti Cheat")) { 
			e.setCancelled(true);
			String name = e.getCurrentItem().getItemMeta().getDisplayName().replace("§a§l", "").replace("§c§l", "");
			ItemStack itm = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			switch(itm.getData().getData()) {
			case 14:
				//ROT
				p.closeInventory();
				Plugin pl = Bukkit.getPluginManager().getPlugin(name);
				LuckPerms lp = LuckPermsProvider.get();
				for(Plugin plugins : Bukkit.getPluginManager().getPlugins()) {
					if(Main.instance.pcfg.contains(plugins.getName())) {
						PermissionNode node = PermissionNode.builder(Main.instance.pcfg.getString(plugins.getName())).build();
						lp.getUserManager().getUser(p.getUniqueId()).data().add(node);
					}
				}
				PermissionNode node = PermissionNode.builder(Main.instance.pcfg.getString(pl.getName())).build();

				lp.getUserManager().getUser(p.getUniqueId()).data().remove(node);
				
				ac.anticheat.put(p, pl.getName());
				
				p.sendMessage(Main.prefix + "§aAnti Cheat wurde zu §e" + name + " §agewechselt!");
				break;
			case 5:
				//GRÜN
				p.closeInventory();
				p.sendMessage(Main.prefix + "§cDas AntiCheat ist bereits aktiviert!");
				break;
			}
		}
	}

}
