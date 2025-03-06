package me.nexipl.bettermc.classes;

import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import java.util.Objects;
import java.util.Random;

public class HerobrineClass {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    private final Random random = new Random();
    public void onHerobrineTotem(BlockIgniteEvent event) {
        if (!plugin.getConfig().getBoolean("herobrine.totemEnable")) return;
        if (Objects.equals(String.valueOf(event.getCause()), "FLINT_AND_STEEL")) {
            World mainWorld = Objects.requireNonNull(event.getPlayer()).getWorld();
            Location blockL1 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-1, event.getBlock().getState().getZ());
            Location blockL2 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-2, event.getBlock().getState().getZ());
            Location blockL3 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-3, event.getBlock().getState().getZ());
            Location blockL4 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-4, event.getBlock().getState().getZ());
            Location blockL5 = new Location(mainWorld, event.getBlock().getState().getX(), event.getBlock().getState().getY()-5, event.getBlock().getState().getZ());

            int minX = -10;
            int maxX = 10;
            int minZ = -10;
            int maxZ = 10;

            int x = random.nextInt(maxX - minX + 1) + minX;
            int z = random.nextInt(maxZ - minZ + 1) + minZ;

            Location spawnZombie = new Location(mainWorld, event.getBlock().getState().getX() + x, mainWorld.getHighestBlockYAt(event.getBlock().getState().getX() + x, event.getBlock().getState().getZ() + z)+1, event.getBlock().getState().getZ() + z);

            String blockT1 = String.valueOf(blockL1.getBlock().getType());
            String blockT2 = String.valueOf(blockL2.getBlock().getType());
            String blockT3 = String.valueOf(blockL3.getBlock().getType());
            String blockT4 = String.valueOf(blockL4.getBlock().getType());
            String blockT5 = String.valueOf(blockL5.getBlock().getType());
            if ((Objects.equals(blockT1, "QUARTZ_BLOCK")) && (Objects.equals(blockT2, "GOLD_BLOCK")) && (Objects.equals(blockT3, "GOLD_BLOCK")) && (Objects.equals(blockT4, "GOLD_BLOCK")) && (Objects.equals(blockT5, "GOLD_BLOCK"))) {
                mainWorld.getBlockAt(blockL1).setType(Material.NETHERRACK);
                mainWorld.strikeLightning(blockL1);
                PigZombie zombie = (PigZombie) mainWorld.spawnEntity(spawnZombie, EntityType.PIG_ZOMBIE);
                zombie.setSilent(true);
                PlayerDisguise disguise = new PlayerDisguise(plugin.getConfig().getString("herobrine.usernameSkin"));
                disguise.setEntity(zombie);
                disguise.startDisguise();
                disguise.getWatcher().setCustomName("Herobrine");
                disguise.getWatcher().setCustomNameVisible(true);
                zombie.setCustomName("Herobrine");
                zombie.setSwimming(true);
                zombie.setMetadata("herobrine", new FixedMetadataValue(plugin, true));

                if (plugin.getConfig().getBoolean("herobrine.armorEnable")) {
                    if (sTA(plugin.getConfig().getString("herobrine.armor.head")) != null) {
                        zombie.getEquipment().setHelmet(new ItemStack(sTA(plugin.getConfig().getString("herobrine.armor.head")), 1));
                    }
                    if (sTA(plugin.getConfig().getString("herobrine.armor.body")) != null) {
                        zombie.getEquipment().setChestplate(new ItemStack(sTA(plugin.getConfig().getString("herobrine.armor.body")), 1));
                    }
                    if (sTA(plugin.getConfig().getString("herobrine.armor.legs")) != null) {
                        zombie.getEquipment().setLeggings(new ItemStack(sTA(plugin.getConfig().getString("herobrine.armor.legs")), 1));
                    }
                    if (sTA(plugin.getConfig().getString("herobrine.armor.foot")) != null) {
                        zombie.getEquipment().setBoots(new ItemStack(sTA(plugin.getConfig().getString("herobrine.armor.foot")), 1));
                    }
                }
                if (sTH(plugin.getConfig().getString("herobrine.armor.rightHand")) != null) {
                    zombie.getEquipment().setItemInMainHand(new ItemStack(sTH(plugin.getConfig().getString("herobrine.armor.leftHand"))));
                }
                if (sTH(plugin.getConfig().getString("herobrine.armor.leftHand")) != null) {
                    zombie.getEquipment().setItemInOffHand(new ItemStack(sTH(plugin.getConfig().getString("herobrine.armor.leftHand"))));
                }
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Herobrine is coming back!");
                }
            }
        }
    }
    public void onHerobrineDeath(EntityDeathEvent event) {
        if (event.getEntity().hasMetadata("herobrine")) {
            event.getDrops().clear();
            if (plugin.getConfig().getBoolean("herobrine.drops.enable")) {
                int min = plugin.getConfig().getInt("herobrine.drops.minQuantity");
                int max = plugin.getConfig().getInt("herobrine.drops.maxQuantity");
                int quantity = random.nextInt(max - min + 1) + min;
                if (sTD(plugin.getConfig().getString("herobrine.drops.item")) != null) {
                    event.getDrops().add(new ItemStack(sTD(plugin.getConfig().getString("herobrine.drops.item")), quantity));
                }
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage("Remember, I always come back!");
            }
        }
    }
    private Material sTA(String material) {
        if (Objects.equals(material, "DIAMOND_LEGGINGS")) {
            return Material.DIAMOND_LEGGINGS;
        }
        else if (Objects.equals(material, "DIAMOND_BOOTS")) {
            return Material.DIAMOND_BOOTS;
        }
        else if (Objects.equals(material, "DIAMOND_CHESTPLATE")) {
            return Material.DIAMOND_CHESTPLATE;
        }
        else if (Objects.equals(material, "DIAMOND_HELMET")) {
            return Material.DIAMOND_HELMET;
        }
        return null;
    }
    private Material sTH(String material) {
        if (Objects.equals(material, "DIAMOND_PICKAXE")) {
            return Material.DIAMOND_PICKAXE;
        }
        return null;
    }
    private  Material sTD(String material) {
        if (Objects.equals(material, "DIAMOND")) {
            return Material.DIAMOND;
        }
        return null;
    }
}