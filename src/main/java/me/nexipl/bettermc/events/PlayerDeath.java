package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.MusicDeathClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        MusicDeathClass deathMusic = new MusicDeathClass();
        deathMusic.onPlayerDeathMusic(event);
    }
}