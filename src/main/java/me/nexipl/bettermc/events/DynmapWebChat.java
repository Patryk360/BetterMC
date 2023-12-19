package me.nexipl.bettermc.events;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.dynmap.DynmapWebChatEvent;

public class DynmapWebChat implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @EventHandler
    public void onDynmapWebChat(DynmapWebChatEvent event) {
        boolean webChatInGame = plugin.getConfig().getBoolean("dynmapWebChatInGame");
        if (webChatInGame) {
            Bukkit.broadcastMessage("[DYNMAP]: " + event.getMessage());
        }
    }
}