package me.nexipl.bettermc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.block.Block.getBlockKey;

public class ListenerBlockIgnite implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (Objects.equals(String.valueOf(event.getCause()), "FLINT_AND_STEEL")) {
            getServer().getLogger().info("ok");
            getServer().getLogger().info(String.valueOf(event.getBlock().getState().getY()));
            getServer().getLogger().info(String.valueOf(getBlockKey(event.getBlock().getState().getX(), event.getBlock().getState().getY()-1, event.getBlock().getState().getZ())));
        }
    }
}
