package me.nexipl.bettermc.commands;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class spawnentity implements CommandExecutor {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());
        assert player != null;
        Location playerLocation = player.getLocation();
        sender.sendMessage("["+plugin.getName()+"] Spawn Entity" + playerLocation);
        plugin.getConfig().getList("custommobs");
        return false;
    }
}