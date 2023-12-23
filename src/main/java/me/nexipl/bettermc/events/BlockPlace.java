package me.nexipl.bettermc.events;

import de.tr7zw.nbtapi.NBTItem;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class BlockPlace implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().toString().contains("CHEST")) {
            ItemStack item = event.getItemInHand();
            NBTItem nbti = new NBTItem(item);
            if (nbti.getBoolean("boxsilver")) {
                Block block = event.getBlock();
                Chest chest = (Chest) block.getState();
                chest.setMetadata("boxsilver", new FixedMetadataValue(plugin, true));
                block.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, block.getLocation(), 100);
            }
        }
    }
}