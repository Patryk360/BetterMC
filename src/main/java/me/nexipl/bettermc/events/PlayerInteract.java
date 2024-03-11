package me.nexipl.bettermc.events;

import de.tr7zw.nbtapi.NBTItem;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Lidded;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

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