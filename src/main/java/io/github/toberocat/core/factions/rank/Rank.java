package io.github.toberocat.core.factions.rank;

import io.github.toberocat.MainIF;
import io.github.toberocat.core.factions.rank.allies.*;
import io.github.toberocat.core.factions.rank.members.*;
import io.github.toberocat.core.utility.Utility;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Rank {

    public static List<Rank> ranks = new ArrayList<>();
    private final String registryName;
    private final String displayName;
    private final boolean isAdmin;

    public Rank(String displayName, String registryName, boolean isAdmin) {
        this.displayName = displayName;
        this.registryName = registryName;
        this.isAdmin = isAdmin;
        ranks.add(this);
    }

    public static void Init() {
        new OwnerRank();
        new AdminRank();
        new ModeratorRank();
        new ElderRank();
        new MemberRank();

        new AllyOwnerRank();
        new AllyAdminRank();
        new AllyModeratorRank();
        new AllyElderRank();
        new AllyMemberRank();

        new GuestRank();
    }

    public static Rank fromString(String str) {
        for (Rank rank : ranks) {
            if (rank.toString().equals(str)) return rank;
        }
        return null;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRegistryName() {
        return registryName;
    }

    public abstract String description(Player player);

    public ItemStack getItem(Player player) {
        return Utility.createItem(Material.GRASS_BLOCK, getDisplayName());
    }

    @Override
    public String toString() {
        return registryName;
    }
}
