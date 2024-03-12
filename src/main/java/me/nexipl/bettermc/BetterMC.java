package me.nexipl.bettermc;

import me.nexipl.bettermc.commands.bettermc;
import me.nexipl.bettermc.commands.bettermcreload;
import me.nexipl.bettermc.commands.getchestbox;
import me.nexipl.bettermc.commands.getchestkey;
import me.nexipl.bettermc.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class BetterMC extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager p = Bukkit.getPluginManager();
        if ((p.getPlugin("LibsDisguises") == null) || (p.getPlugin("ProtocolLib") == null)|| (p.getPlugin("NBTAPI") == null)) {
            getLogger().warning("Couldn't find LibsDisguises, ProtocolLib, NBTAPI please install it! Plugin is disabling now!");
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            this.saveDefaultConfig();
            getServer().getPluginManager().registerEvents(new BlockIgnite(), this);
            getServer().getPluginManager().registerEvents(new EntityDeath(), this);
            getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
            getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
            if (p.getPlugin("GMusic") != null) getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
            getServer().getPluginManager().registerEvents(new BlockPlace(), this);

            Objects.requireNonNull(this.getCommand("bettermcreload")).setExecutor(new bettermcreload());
            Objects.requireNonNull(this.getCommand("bettermc")).setExecutor(new bettermc());
            Objects.requireNonNull(this.getCommand("getchestbox")).setExecutor(new getchestbox());
            Objects.requireNonNull(this.getCommand("getchestkey")).setExecutor(new getchestkey());

            getLogger().info("Plugin is enabling! Have a nice day!");
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is disabling! Goodbye World!");
    }
}