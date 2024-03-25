package me.nexipl.bettermc.classes;

import de.tr7zw.nbtapi.NBTItem;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Lidded;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class ChestBoxClass {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    public void onChestBoxOpen(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getType().toString().contains("CHEST")) {
                Block block = event.getClickedBlock();
                BlockState chest = block.getState();
                if (chest instanceof Chest) {
                    if (chest.hasMetadata("boxsilver")) {
                        Location location = event.getClickedBlock().getLocation();
                        ItemStack itemStack1 = new ItemStack(Material.DIAMOND, 5);
                        Lidded chestA = (Lidded) chest;
                        chestA.open();
                        block.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, block.getLocation(), 200);
                        Bukkit.getScheduler().runTaskLater(plugin, () -> {
                            location.getWorld().dropItem(location, itemStack1);
                            event.getClickedBlock().removeMetadata("boxsilver", plugin);
                            event.getClickedBlock().setType(Material.AIR);
                            spawnFirework(location);
                            spawnFirework(location);
                            spawnFirework(location);
                        }, 10L);
                    }
                    if (event.hasItem()) {
                        ItemStack item = event.getItem();
                        if (item.getType() != Material.AIR) {
                            NBTItem nbti = new NBTItem(item);
                            if (!nbti.getString("keyPass").isEmpty()) {
                                if (!chest.hasMetadata("keyPass")) {
                                    chest.setMetadata("keyPass", new FixedMetadataValue(plugin, nbti.getString("keyPass")));
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
        }
    }
    public void onChestBoxPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType().toString().contains("CHEST")) {
            ItemStack item = event.getItemInHand();
            NBTItem nbti = new NBTItem(item);
            if (nbti.getBoolean("boxsilver")) {
                Block block = event.getBlock();
                Chest chest = (Chest) block.getState();
                chest.setMetadata("boxsilver", new FixedMetadataValue(plugin, true));
                block.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, block.getLocation(), 100);
            }
        }
    }
    private void spawnFirework(Location location) {
        Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        FireworkEffect effect = FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .with(FireworkEffect.Type.BURST)
                .withColor(Color.RED)
                .withFade(Color.YELLOW)
                .build();

        fireworkMeta.addEffect(effect);
        fireworkMeta.setPower(1);

        firework.setFireworkMeta(fireworkMeta);
    }
}