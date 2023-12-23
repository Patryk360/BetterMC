package me.nexipl.bettermc.commands;

import de.tr7zw.nbtapi.NBTItem;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import java.util.UUID;

public class getchestkey implements CommandExecutor {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
            NBTItem nbti = new NBTItem(key);
            nbti.setString("keyPass", UUID.randomUUID().toString());
            key = nbti.getItem();

            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(key);
                player.sendMessage("Otrzymałeś klucz do skrzynki!");
            }
        } else {
            sender.sendMessage("["+plugin.getName()+"] Musisz być graczem!");
            return false;
        }
        return false;
    }
}