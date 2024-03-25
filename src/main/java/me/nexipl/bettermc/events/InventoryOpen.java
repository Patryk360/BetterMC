package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.ChestBoxClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpen implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        ChestBoxClass chestBox = new ChestBoxClass();
        chestBox.onChestBoxOpenInv(event);
    }
}