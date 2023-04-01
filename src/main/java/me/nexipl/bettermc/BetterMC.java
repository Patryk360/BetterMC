package me.nexipl.bettermc;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class BetterMC extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ListenerBlockIgnite(), this);
        Objects.requireNonNull(this.getCommand("distest")).setExecutor(new ListenerCommand());
        getLogger().info("Hello World!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye World!");
    }
}