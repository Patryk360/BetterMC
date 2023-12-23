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

public class getchestbox implements CommandExecutor {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ItemStack chestBoxSilver = new ItemStack(Material.CHEST);
            NBTItem nbti = new NBTItem(chestBoxSilver);
            nbti.setBoolean("boxsilver", true);
            chestBoxSilver = nbti.getItem();

            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(chestBoxSilver);
                player.sendMessage("Otrzymałeś skrzynkę!");
            } else {
                player.sendMessage("Nie masz wolnego miejsca w ekwipunku!");
            }
            sender.sendMessage("["+plugin.getName()+"] Get Chest Box Command");
        } else {
            sender.sendMessage("["+plugin.getName()+"] Musisz być graczem!");
        }
        return false;
    }
}