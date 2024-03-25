package me.nexipl.bettermc.classes;

import dev.geco.gmusic.main.GMusicMain;
import dev.geco.gmusic.objects.Song;
import me.nexipl.bettermc.BetterMC;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class MusicDeathClass {
    private final Plugin plugin = BetterMC.getPlugin(BetterMC.class);
    PluginManager pluginManager = plugin.getServer().getPluginManager();
    public void onPlayerDeathMusic(PlayerDeathEvent event) {
        if (plugin.getConfig().getBoolean("soundOnDeath.enable")) {
            if (pluginManager.getPlugin("GMusic") != null) {
                GMusicMain gMusicMain = GMusicMain.getInstance();
                Song song = gMusicMain.getSongManager().getSongById(plugin.getConfig().getString("soundOnDeath.soundId"));
                gMusicMain.getSongManager().playSong(event.getEntity(), song);
            }
        }
    }
}