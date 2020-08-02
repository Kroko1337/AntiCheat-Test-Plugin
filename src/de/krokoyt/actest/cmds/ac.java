package de.krokoyt.actest.cmds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import de.krokoyt.actest.Main;

public class ac implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (s instanceof Player) {
			Player p = (Player) s;
			openInventory(p);
		}
		return false;
	}

	public static HashMap<Player, String> anticheat = new HashMap<Player, String>();

	public static void openInventory(Player p) {
		int curItem = 0;
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§eSelect Anti Cheat");
		
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack itm = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte)15);
			ItemMeta im = itm.getItemMeta();
			im.setDisplayName("§b");
			im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			itm.setItemMeta(im);
			inv.setItem(i, itm);
		}
		
		for (int i = 0; i < Bukkit.getPluginManager().getPlugins().length; i++) {
			Plugin pl = Bukkit.getPluginManager().getPlugins()[i];
			
				if (pl.isEnabled()) {
					if (!Main.instance.cfg.getList("API-List").contains(pl.getName())) {
						if (!pl.getName().equalsIgnoreCase(Main.instance.getName())) {
							ItemStack itm = new ItemStack(Material.WOOL, 1,
									anticheat.containsKey(p) && anticheat.get(p).equalsIgnoreCase(pl.getName())
											? (byte) 5
											: (byte) 14); // 14 5
							ItemMeta im = itm.getItemMeta();
							im.setDisplayName(
									"§" + (anticheat.containsKey(p) && anticheat.get(p).equalsIgnoreCase(pl.getName())
											? "a§l"
											: "c§l") + pl.getName());
							List<String> lore = new ArrayList<String>();
							lore.add("§fVersion: §6" + pl.getDescription().getVersion());
							lore.add("§fAuthors: §6" + pl.getDescription().getAuthors());
							lore.add("§fDescription: §6" + pl.getDescription().getDescription());
							im.setLore(lore);
							
							if(anticheat.containsKey(p) && anticheat.get(p).equalsIgnoreCase(pl.getName())) {
							itm.addUnsafeEnchantment(Enchantment.LUCK, 1);
							im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							}
							itm.setItemMeta(im);
							inv.setItem(curItem, itm);
							curItem++;
						}
					}
				
			}
		}
		p.openInventory(inv);
	}

}
