package me.nexipl.bettermc.events;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EntityDeath implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getName().equals("Herobrine")) {
            event.getDrops().clear();
            event.getDrops().add(new ItemStack(Material.DIAMOND, plugin.getConfig().getInt("herobrine.drops.quantity")));
        }
    }
}