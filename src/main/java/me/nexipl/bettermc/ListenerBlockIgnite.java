package me.nexipl.bettermc;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class ListenerBlockIgnite implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (Objects.equals(String.valueOf(event.getCause()), "FLINT_AND_STEEL")) {
            World mainWorld = Objects.requireNonNull(event.getPlayer()).getWorld();
            Location blockL1 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-1, event.getBlock().getState().getZ());
            Location blockL2 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-2, event.getBlock().getState().getZ());
            Location blockL3 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-3, event.getBlock().getState().getZ());
            Location blockL4 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-4, event.getBlock().getState().getZ());
            Location blockL5 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-5, event.getBlock().getState().getZ());

            Location spawnZombie = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-5, event.getBlock().getState().getZ()+5);

            String blockT1 = String.valueOf(blockL1.getBlock().getType());
            String blockT2 = String.valueOf(blockL2.getBlock().getType());
            String blockT3 = String.valueOf(blockL3.getBlock().getType());
            String blockT4 = String.valueOf(blockL4.getBlock().getType());
            String blockT5 = String.valueOf(blockL5.getBlock().getType());
            if ((Objects.equals(blockT1, "QUARTZ_BLOCK")) && (Objects.equals(blockT2, "GOLD_BLOCK")) && (Objects.equals(blockT3, "GOLD_BLOCK")) && (Objects.equals(blockT4, "GOLD_BLOCK")) && (Objects.equals(blockT5, "GOLD_BLOCK"))) {
                mainWorld.getBlockAt(blockL1).setType(Material.NETHERRACK);
                mainWorld.strikeLightning(blockL1);
                Zombie zombie = (Zombie) mainWorld.spawnEntity(spawnZombie, EntityType.ZOMBIFIED_PIGLIN);
                zombie.setSilent(true);
                zombie.customName(Component.text("Herobrine"));
                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 6));
                zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
                zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
                zombie.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Herobrine is coming back!");
                }
            }
        }
    }
}
