package io.github.toberocat.improvedfactions.spigot.gui.provided;

import io.github.toberocat.improvedFactions.core.gui.ItemContainer;
import io.github.toberocat.improvedFactions.core.gui.JsonGui;
import io.github.toberocat.improvedFactions.core.handler.MessageHandler;
import io.github.toberocat.improvedFactions.core.player.FactionPlayer;
import io.github.toberocat.improvedFactions.core.translator.layout.item.XmlItem;
import io.github.toberocat.improvedFactions.core.utils.Logger;
import io.github.toberocat.improvedfactions.spigot.MainIF;
import io.github.toberocat.improvedfactions.spigot.gui.AbstractGui;
import io.github.toberocat.improvedfactions.spigot.gui.page.Page;
import io.github.toberocat.improvedfactions.spigot.gui.settings.GuiSettings;
import io.github.toberocat.improvedfactions.spigot.item.SpigotItemStack;
import io.github.toberocat.improvedfactions.spigot.player.SpigotFactionPlayer;
import io.github.toberocat.improvedfactions.spigot.utils.ItemUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static io.github.toberocat.improvedfactions.spigot.utils.ItemUtils.ORIGINAL_NAME_KEY;
import static io.github.toberocat.improvedfactions.spigot.utils.ItemUtils.resetTranslation;

public class SpigotEditJsonGui extends AbstractGui {

    private final String[] slotActions;
    private final JsonGui jsonGui;

    public SpigotEditJsonGui(@NotNull Player player, @NotNull JsonGui jsonGui) {
        super(player, createInv(player, jsonGui));
        slotActions = new String[inventory.getSize()];
        this.jsonGui = jsonGui;

        ItemStack[] content = new org.bukkit.inventory.ItemStack[inventory.getSize()];
        jsonGui.getContent().forEach((item, action) -> {
            if (item.slot() >= slotActions.length) return;

            slotActions[item.slot()] = action;
            ItemStack stack = (ItemStack) item.stack().getRaw();
            ItemUtils.translateItem(new SpigotFactionPlayer(player), jsonGui.getGuiId(), stack);

            content[item.slot()] = stack;
        });
        inventory.setContents(content);

        close = () -> {
            updateContent();
            jsonGui.write();
        };
    }

    private static Inventory createInv(@NotNull Player player, @NotNull JsonGui jsonGui) {
        return AbstractGui.createInventory(player, AbstractGui.clamp(jsonGui.getRows() * 9, 9, 54), jsonGui.getTitle());
    }

    private void updateContent() {
        io.github.toberocat.improvedFactions.core.item.ItemStack[] stacks = Arrays.stream(inventory.getContents())
                .peek(ItemUtils::resetTranslation)
                .map(SpigotItemStack::new)
                .toArray(SpigotItemStack[]::new);
        Map<ItemContainer, String> actionMap = new HashMap<>();
        for (int i = 0; i < stacks.length; i++) {
            io.github.toberocat.improvedFactions.core.item.ItemStack stack = stacks[i];
            if (stack == null) continue;

            String actions = slotActions[i];
            actionMap.put(new ItemContainer(i, stack), actions);
        }

        jsonGui.setContent(actionMap);
    }

    @Override
    protected void addPage() {
        pages.add(new Page(inventory.getSize()));
    }

    @Override
    public void click(InventoryClickEvent event) {
        super.click(event);
        if (event.getCursor() != null) event.setCancelled(false);
    }

    @Override
    public void drag(InventoryDragEvent event) {
        super.drag(event);
        if (event.getCursor() != null) event.setCancelled(false);
    }

    @Override
    protected GuiSettings readSettings() {
        return new GuiSettings();
    }
}
