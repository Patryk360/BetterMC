package me.nexipl.bettermc;

import me.nexipl.bettermc.commands.distest;
import me.nexipl.bettermc.commands.spawnentity;
import me.nexipl.bettermc.events.ListenerBlockIgnite;
import me.nexipl.bettermc.events.ListenerEntityDeath;
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
            getServer().getPluginManager().registerEvents(new ListenerBlockIgnite(), this);
            getServer().getPluginManager().registerEvents(new ListenerEntityDeath(), this);
            Objects.requireNonNull(this.getCommand("distest")).setExecutor(new distest());
            Objects.requireNonNull(this.getCommand("spawnentity")).setExecutor(new spawnentity());
            getLogger().info("Plugin is enabling! Have a nice day!");
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Goodbye World!");
    }
}