package me.nexipl.bettermc.commands;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class hideandseek implements CommandExecutor {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            sender.sendMessage("OK");
        } else {
            sender.sendMessage("["+plugin.getName()+"] Musisz być graczem!");
            return false;
        }
        return false;
    }
}