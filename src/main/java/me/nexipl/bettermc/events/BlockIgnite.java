package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.HerobrineClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockIgnite implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        HerobrineClass hero = new HerobrineClass();
        hero.onHerobrineTotem(event);
    }
}