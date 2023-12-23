package me.nexipl.bettermc.events;

import de.tr7zw.nbtapi.NBTItem;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Lidded;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import static org.apache.logging.log4j.LogManager.getLogger;

public class PlayerInteract implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @EventHandler
    public void onChestOpen(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType().toString().contains("CHEST")) {
                Block block = event.getClickedBlock();
                Chest chest = (Chest) block.getState();
                if (chest.hasMetadata("boxsilver")) {
                    Location location = event.getClickedBlock().getLocation();
                    ItemStack itemStack1 = new ItemStack(Material.DIAMOND, 5);
                    Lidded chestA = (Lidded) chest;
                    chestA.open();
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        location.getWorld().dropItem(location, itemStack1);
                        event.getClickedBlock().setType(Material.AIR);
                        block.getLocation().getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, block.getLocation(), 50);
                    }, 20L);
                }
                if (event.hasItem()) {
                    ItemStack item = event.getItem();
                    getLogger().info(item.getType());
                    if (item.getType() != Material.AIR) {
                        NBTItem nbti = new NBTItem(item);
                        getLogger().info(nbti.getString("keyPass"));
                        if (!chest.hasMetadata("keyPass")) {
                            getLogger().info("chest is not locked");
                            chest.setMetadata("keyPass", new FixedMetadataValue(plugin, nbti.getString("keyPass")));
                        }
                        getLogger().info(chest.hasMetadata("keyPass"));
                    } else {
                        if (chest.hasMetadata("keyPass")) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}