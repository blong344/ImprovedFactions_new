package io.github.toberocat.improvedFactions.core.faction.components.report;

import io.github.toberocat.improvedFactions.core.player.OfflineFactionPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface FactionReports {
    void addReport(@NotNull OfflineFactionPlayer<?> reporter, @NotNull String reason);

    @NotNull Stream<Report> getReports();
}
