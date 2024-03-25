package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.HerobrineClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        HerobrineClass Herobrine = new HerobrineClass();
        Herobrine.onHerobrineDeath(event);
    }
}