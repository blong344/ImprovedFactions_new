package io.github.toberocat.improvedFactions.core.player;

import io.github.toberocat.improvedFactions.core.location.Location;
import io.github.toberocat.improvedFactions.core.translator.Placeholder;
import io.github.toberocat.improvedFactions.core.translator.layout.Translatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public interface FactionPlayer<P> extends CommandSender, OfflineFactionPlayer<P> {

    @Nullable String getMessage(@NotNull Function<Translatable, String> query, Placeholder... placeholders);

    @Nullable String[] getMessageBatch(@NotNull Function<Translatable, String[]> query, Placeholder... placeholders);

    @NotNull Location getLocation();

    @NotNull String getLocal();

    void sendTitle(@NotNull String title, @NotNull String subtitle);

    void sendTitle(@NotNull Function<Translatable, String> title);
}
