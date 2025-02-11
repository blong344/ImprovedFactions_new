package io.github.toberocat.improvedfactions.spigot.listener;

import io.github.toberocat.improvedfactions.spigot.MainIF;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SpigotEventListener implements Listener {

    protected final MainIF plugin;

    public SpigotEventListener(@NotNull MainIF plugin) {
        this.plugin = plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


}
