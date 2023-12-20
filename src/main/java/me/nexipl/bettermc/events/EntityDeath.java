package me.nexipl.bettermc.events;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import java.util.Random;

public class EntityDeath implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    private final Random random = new Random();
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!event.getEntity().getMetadata("herobrine").isEmpty()) {
            event.getDrops().clear();
            if (plugin.getConfig().getBoolean("herobrine.drops.enable")) {
                int min = plugin.getConfig().getInt("herobrine.drops.minQuantity");
                int max = plugin.getConfig().getInt("herobrine.drops.maxQuantity");
                int quantity = random.nextInt(max - min + 1) + min;
                event.getDrops().add(new ItemStack(Material.DIAMOND, quantity));
            }
        }
    }
}