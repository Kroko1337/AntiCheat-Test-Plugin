package de.krokoyt.switcher.utils;

import de.krokoyt.switcher.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {

    public static void openAntiCheatSelection(Player player) {
        final Inventory inventory = Bukkit.createInventory(null, 54, "§eSelect AntiCheat");
        for(int i = 0; i < inventory.getSize(); i++) {
            final ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("");
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(i, itemStack);
        }
        int curItem = 1;

        final boolean selectedVanilla = Main.getAntiCheat(player).equalsIgnoreCase("Vanilla");
        final ItemStack vanillaStack = new ItemStack(Material.WOOL, 1, (short) (selectedVanilla ? 5 : 14));
        final ItemMeta vanillaMeta = vanillaStack.getItemMeta();
        vanillaMeta.setDisplayName((selectedVanilla ? "§a" : "§c") + "§lVanilla");
        final List<String> vanillaLore = new ArrayList<>();
        vanillaLore.add("§fVersion: §6" + Bukkit.getServer().getVersion());
        vanillaLore.add("§fAuthors: §6Mojang");
        vanillaLore.add("§fDescription: §6null");
        vanillaLore.add("§fWebsite: §6https://www.minecraft.net");
        vanillaMeta.setLore(vanillaLore);
        if(selectedVanilla) {
            vanillaStack.addUnsafeEnchantment(Enchantment.LUCK, 1);
            vanillaMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        vanillaStack.setItemMeta(vanillaMeta);
        inventory.setItem(0, vanillaStack);

        for(int pl = 0; pl < Bukkit.getPluginManager().getPlugins().length; pl++) {
            final Plugin plugin = Bukkit.getPluginManager().getPlugins()[pl];
            if(plugin.isEnabled() && Main.instance.permissionConfiguration.contains(plugin.getName())) {
                final boolean hasSelected = Main.getAntiCheat(player).equalsIgnoreCase(plugin.getName());
                final ItemStack itemStack = new ItemStack(Material.WOOL, 1, (short) (hasSelected ? 5 : 14));
                final ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName((hasSelected ? "§a" : "§c") + "§l" + plugin.getName());
                final List<String> lore = new ArrayList<>();
                lore.add("§fVersion: §6" + plugin.getDescription().getVersion());
                lore.add("§fAuthors: §6" + plugin.getDescription().getAuthors());
                lore.add("§fDescription: §6" + plugin.getDescription().getDescription());
                lore.add("§fWebsite: §6" + plugin.getDescription().getWebsite());
                itemMeta.setLore(lore);
                if(hasSelected) {
                    itemStack.addUnsafeEnchantment(Enchantment.LUCK, 1);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(curItem, itemStack);
                curItem++;
            }
        }
        player.openInventory(inventory);
    }
}
