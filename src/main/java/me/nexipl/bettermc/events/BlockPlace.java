package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.ChestBoxClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ChestBoxClass chestBox = new ChestBoxClass();
        chestBox.onChestBoxPlace(event);
    }
}