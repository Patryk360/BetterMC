package me.nexipl.bettermc;

import me.nexipl.bettermc.commands.bettermcreload;
import me.nexipl.bettermc.commands.distest;
import me.nexipl.bettermc.commands.spawnentity;
import me.nexipl.bettermc.events.BlockIgnite;
import me.nexipl.bettermc.events.EntityDeath;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class BetterMC extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager p = Bukkit.getPluginManager();
        if ((p.getPlugin("LibsDisguises") == null)) {
            getLogger().warning("Couldn't find LibsDisguises, please install it! Plugin is disabling now!");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            this.saveDefaultConfig();
            getServer().getPluginManager().registerEvents(new BlockIgnite(), this);
            getServer().getPluginManager().registerEvents(new EntityDeath(), this);
            Objects.requireNonNull(this.getCommand("distest")).setExecutor(new distest());
            Objects.requireNonNull(this.getCommand("spawnentity")).setExecutor(new spawnentity());
            Objects.requireNonNull(this.getCommand("bettermcreload")).setExecutor(new bettermcreload());
            getLogger().info("Plugin is enabling! Have a nice day!");
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is disabling! Goodbye World!");
    }
}