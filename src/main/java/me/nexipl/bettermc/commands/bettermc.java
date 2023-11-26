package me.nexipl.bettermc.commands;

import me.nexipl.bettermc.BetterMC;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class bettermc implements CommandExecutor {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Economy economy = Objects.requireNonNull(plugin.getServer().getServicesManager().getRegistration(Economy.class)).getProvider();
        double bal = economy.getBalance(sender.getName());
        sender.sendMessage("["+plugin.getName()+"] " + "Your balance is: " + bal);
        return false;
    }
}
