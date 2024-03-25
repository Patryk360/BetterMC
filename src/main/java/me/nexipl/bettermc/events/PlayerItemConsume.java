package me.nexipl.bettermc.events;

import me.nexipl.bettermc.classes.FoodClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerItemConsume implements Listener {
    @EventHandler
    public void onItemConsume (PlayerItemConsumeEvent event) {
        FoodClass milk = new FoodClass();
        milk.onMilkDrink(event);
    }
}
