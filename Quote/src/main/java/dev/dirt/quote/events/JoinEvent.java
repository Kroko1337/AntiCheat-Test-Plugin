package dev.dirt.quote.events;

import dev.dirt.quote.Main;
import dev.dirt.core.utils.NMSUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater((Plugin) Main.getPlugin(Main.class), () -> {
            NMSUtil.sendTabList("Test Server\n", "\nHere is a quote for you:\n" + Main.getRandomTabListMessage().replace("{player}", player.getName()), player);
        }, 0L);
    }
}
