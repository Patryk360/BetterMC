package me.nexipl.bettermc;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
//import static org.bukkit.Bukkit.getServer;

import java.util.Objects;

public class ListenerBlockIgnite implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (Objects.equals(String.valueOf(event.getCause()), "FLINT_AND_STEEL")) {
            World mainWorld = Objects.requireNonNull(event.getPlayer()).getWorld();
            Location blockL1 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-1, event.getBlock().getState().getZ());
            Location blockL2 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-2, event.getBlock().getState().getZ());
            Location blockL3 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-3, event.getBlock().getState().getZ());
            Location blockL4 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-4, event.getBlock().getState().getZ());
            Location blockL5 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-5, event.getBlock().getState().getZ());

            Location spawnZombie = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-5, event.getBlock().getState().getZ()+5);

            String blockT1 = String.valueOf(blockL1.getBlock().getType());
            String blockT2 = String.valueOf(blockL2.getBlock().getType());
            String blockT3 = String.valueOf(blockL3.getBlock().getType());
            String blockT4 = String.valueOf(blockL4.getBlock().getType());
            String blockT5 = String.valueOf(blockL5.getBlock().getType());
            if ((Objects.equals(blockT1, "QUARTZ_BLOCK")) && (Objects.equals(blockT2, "GOLD_BLOCK")) && (Objects.equals(blockT3, "GOLD_BLOCK")) && (Objects.equals(blockT4, "GOLD_BLOCK")) && (Objects.equals(blockT5, "GOLD_BLOCK"))) {
                mainWorld.spawnEntity(spawnZombie, EntityType.ZOMBIE);
            }
        }
    }
}
