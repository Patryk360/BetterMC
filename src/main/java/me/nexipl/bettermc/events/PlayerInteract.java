package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.ChestBoxClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        ChestBoxClass chestBox = new ChestBoxClass();
        chestBox.onChestBoxOpen(event);
    }
}