package me.nexipl.bettermc;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterMC extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello World!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye World!");
    }
}