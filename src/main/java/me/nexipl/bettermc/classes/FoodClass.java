package me.nexipl.bettermc.classes;

import me.nexipl.bettermc.BetterMC;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;
import java.util.Objects;

public class FoodClass {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    public void onMilkDrink(PlayerItemConsumeEvent event) {
        String typeItem = event.getItem().getType().toString();
        if (Objects.equals(typeItem, "MILK_BUCKET")) {
            if (plugin.getConfig().getBoolean("milk.enable")) {
                Player playerConsumeMilk = event.getPlayer();
                int setHunger = playerConsumeMilk.getFoodLevel();
                float setSaturation = playerConsumeMilk.getSaturation();
                playerConsumeMilk.setFoodLevel(setHunger + plugin.getConfig().getInt("milk.hunger"));
                playerConsumeMilk.setSaturation(setSaturation + plugin.getConfig().getInt("milk.saturation"));
            }
        }
    }
}