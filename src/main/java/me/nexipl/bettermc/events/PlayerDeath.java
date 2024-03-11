package me.nexipl.bettermc.events;

import dev.geco.gmusic.main.GMusicMain;
import dev.geco.gmusic.objects.Song;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerDeath implements Listener {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("soundOnDeath.enable")) {
            GMusicMain gMusicMain = GMusicMain.getInstance();
            Song song = gMusicMain.getSongManager().getSongById(plugin.getConfig().getString("soundOnDeath.soundId"));
            gMusicMain.getSongManager().playSong(event.getEntity(), song);
        }
    }
}