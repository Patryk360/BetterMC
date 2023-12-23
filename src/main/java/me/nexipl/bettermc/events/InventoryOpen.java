package me.nexipl.bettermc.events;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import static org.apache.logging.log4j.LogManager.getLogger;

public class InventoryOpen implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        Block block = event.getInventory().getLocation().getBlock();
        if (block.getState() instanceof Chest chest) {
            if (block.hasMetadata("boxsilver")) {
                event.setCancelled(true);
            }
            ItemStack itemHand = event.getPlayer().getInventory().getItemInMainHand();
            getLogger().info(itemHand.getType());
            if (itemHand.getType() != Material.AIR) {
                if (chest.hasMetadata("keyPass")) {
                    NBTItem nbti = new NBTItem(itemHand);
                    String chestKey = chest.getMetadata("keyPass").get(0).asString();
                    if (!chestKey.equals(nbti.getString("keyPass"))) {
                        event.setCancelled(true);
                    }
                }
            } else {
                if (chest.hasMetadata("keyPass")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}