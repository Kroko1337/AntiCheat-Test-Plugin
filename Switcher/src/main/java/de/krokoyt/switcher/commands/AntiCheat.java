package de.krokoyt.switcher.commands;

import de.krokoyt.switcher.utils.InventoryUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiCheat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            InventoryUtil.openAntiCheatSelection(player);
        }
        return false;
    }
}
