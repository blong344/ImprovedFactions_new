package io.github.toberocat.improvedFactions.core.faction.database.mysql;

import io.github.toberocat.improvedFactions.core.database.DatabaseHandle;
import io.github.toberocat.improvedFactions.core.database.DatabaseVar;
import io.github.toberocat.improvedFactions.core.database.mysql.MySqlDatabase;
import io.github.toberocat.improvedFactions.core.database.mysql.builder.Select;
import io.github.toberocat.improvedFactions.core.event.EventExecutor;
import io.github.toberocat.improvedFactions.core.exceptions.faction.FactionAlreadyExistsException;
import io.github.toberocat.improvedFactions.core.exceptions.faction.FactionNotInStorage;
import io.github.toberocat.improvedFactions.core.exceptions.faction.IllegalFactionNamingException;
import io.github.toberocat.improvedFactions.core.faction.components.rank.GuestRank;
import io.github.toberocat.improvedFactions.core.faction.components.rank.Rank;
import io.github.toberocat.improvedFactions.core.faction.components.rank.members.FactionRank;
import io.github.toberocat.improvedFactions.core.faction.handler.FactionHandlerInterface;
import io.github.toberocat.improvedFactions.core.persistent.PersistentHandler;
import io.github.toberocat.improvedFactions.core.player.FactionPlayer;
import io.github.toberocat.improvedFactions.core.player.OfflineFactionPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class MySqlFactionHandler implements FactionHandlerInterface<MySqlFaction> {
    private static MySqlFactionHandler instance;

    private final Map<String, MySqlFaction> factions = new HashMap<>();
    private final MySqlDatabase database;

    public MySqlFactionHandler() {
        database = DatabaseHandle.requestMySql();

        instance = this;
    }

    /**
     * Gives the current handler instance.
     * Can be null, because it only got instanced when a mysql got loaded
     *
     * @return This instance of the database handler
     */
    public static @Nullable MySqlFactionHandler getInstance() {
        return instance;
    }

    @Override
    public @NotNull MySqlFaction create(@NotNull String display, @NotNull FactionPlayer<?> owner)
            throws IllegalFactionNamingException, FactionAlreadyExistsException {
        MySqlFaction faction = new MySqlFaction(display, owner);
        owner.getDataContainer().set(PersistentHandler.FACTION_KEY, faction.getRegistry());

        EventExecutor.getExecutor().createFaction(faction, owner);
        return faction;
    }

    @Override
    public @NotNull MySqlFaction load(@NotNull String registry) throws FactionNotInStorage {
        int size = database.rowSelect(new Select()
                        .setTable("factions")
                        .setColumns("")
                        .setFilter("registry = %s", registry))
                .getRows()
                .size();
        if (size != 1) throw new FactionNotInStorage(registry, FactionNotInStorage.StorageType.DATABASE);
        return new MySqlFaction(registry);
    }

    @Override
    public boolean isLoaded(@NotNull String registry) {
        return factions.containsKey(registry);
    }

    @Override
    public boolean exists(@NotNull String registry) {
        if (isLoaded(registry)) return true;
        return database.rowSelect(new Select()
                        .setTable("factions")
                        .setColumns("")
                        .setFilter("registry = %s", registry))
                .getRows()
                .size() == 1;
    }

    @Override
    public @NotNull Map<String, MySqlFaction> getLoadedFactions() {
        return factions;
    }

    @Override
    public @NotNull Stream<String> getAllFactions() {
        return database.rowSelect(new Select()
                        .setTable("factions")
                        .setColumns("registry"))
                .getRows()
                .stream()
                .map(x -> x.get("registry").toString());
    }

    @Override
    public void deleteCache(@NotNull String registry) {
        factions.remove(registry);
    }

    @Override
    public void deleteFromFile(@NotNull String registry) {
        factions.remove(registry);
        database.executeUpdate(MySqlDatabase.DELETE_FACTION, DatabaseVar.of("registry", registry));
    }

    public @NotNull FactionRank getSavedRank(@NotNull UUID player) {
        return (FactionRank) Rank.fromString(database.rowSelect(new Select()
                        .setTable("players")
                        .setColumns("member_rank")
                        .setFilter("uuid = %s", player.toString()))
                .readRow(String.class, "member_rank")
                .orElse(GuestRank.REGISTRY));
    }
}
