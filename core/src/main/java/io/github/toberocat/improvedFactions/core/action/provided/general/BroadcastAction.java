package io.github.toberocat.improvedFactions.core.action.provided.general;

import io.github.toberocat.improvedFactions.core.action.Action;
import io.github.toberocat.improvedFactions.core.handler.ImprovedFactions;
import io.github.toberocat.improvedFactions.core.handler.MessageHandler;
import io.github.toberocat.improvedFactions.core.player.CommandSender;
import io.github.toberocat.improvedFactions.core.player.FactionPlayer;
import org.jetbrains.annotations.NotNull;

public class BroadcastAction extends Action {

    @Override
    public @NotNull String label() {
        return "broadcast";
    }

    @Override
    public void run(@NotNull CommandSender commandSender, @NotNull String provided) {
        ImprovedFactions.api().broadcast(MessageHandler.api().format(provided));
    }

    @Override
    public void run(@NotNull FactionPlayer<?> player, @NotNull String provided) {
        ImprovedFactions.api().broadcast(MessageHandler.api().format(player, provided));
    }
}
