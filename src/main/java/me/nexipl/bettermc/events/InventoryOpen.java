package me.nexipl.bettermc.events;

import org.bukkit.block.Block;
import org.bukkit.block.Lockable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpen implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Block block = event.getInventory().getLocation().getBlock();
        if (block.getType().toString().contains("CHEST")) {
            Lockable lockable = (Lockable) block.getState();
            if (block.hasMetadata("boxsilver")) {
                event.setCancelled(true);
            }
            if (lockable.isLocked()) {
                if (!lockable.getLock().equals(((Lockable) block.getState()).getLock())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}