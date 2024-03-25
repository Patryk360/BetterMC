package me.nexipl.bettermc.classes;

import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
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
                Zombie zombie = (Zombie) mainWorld.spawnEntity(spawnZombie, EntityType.PIG_ZOMBIE);
                zombie.setSilent(true);
                PlayerDisguise disguise = new PlayerDisguise(plugin.getConfig().getString("herobrine.usernameSkin"));
                disguise.setEntity(zombie);
                disguise.startDisguise();
                disguise.getWatcher().setCustomName("Herobrine");
                disguise.getWatcher().setCustomNameVisible(true);
                zombie.setCustomName("Herobrine");
                zombie.setSwimming(true);
                zombie.setVelocity(zombie.getLocation().getDirection().multiply(10.0));
                zombie.setMetadata("herobrine", new FixedMetadataValue(plugin, true));
                if (plugin.getConfig().getBoolean("herobrine.armorEnable")) {
                    zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
                    zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                    zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
                    zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
                }
                zombie.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
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
                event.getDrops().add(new ItemStack(Material.DIAMOND, quantity));
            }
        }
    }
}