package de.krokoyt.switcher.commands;

import de.krokoyt.core.Core;
import de.krokoyt.switcher.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class AddAntiCheat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            if(args.length == 3) {
                final String plugin = args[0];
                final String permission = args[1];
                final String prefix = args[2];
                if(player.hasPermission("switcher.addanticheat")) {
                    Main.instance.permissionConfiguration.set(plugin + ".permission", permission);
                    Main.instance.permissionConfiguration.set(plugin + ".prefix", prefix);
                    try {
                        Main.instance.permissionConfiguration.save(Main.instance.permissionFile);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    player.sendMessage(Core.PREFIX + "§aAntiCheat: §e" + plugin + " §7(" + prefix.replace("&","§") + "§7) §aadded, with the permission: §e" + permission + "§a!");
                } else {
                    player.sendMessage(Core.PREFIX + "§cThe Plugin is already added!");
                }
            } else {
                player.sendMessage(Core.PREFIX + "§cWrong Usage: §7/addac <Plugin> <Permission> <Prefix>");
            }
        }
        return false;
    }
}
