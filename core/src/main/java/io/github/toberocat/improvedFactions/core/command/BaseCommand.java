package io.github.toberocat.improvedFactions.core.command;

import io.github.toberocat.improvedFactions.core.command.component.Command;
import io.github.toberocat.improvedFactions.core.command.component.CommandSettings;
import io.github.toberocat.improvedFactions.core.command.sub.admin.AdminRoot;
import io.github.toberocat.improvedFactions.core.command.sub.chunk.ClaimCommand;
import io.github.toberocat.improvedFactions.core.command.sub.chunk.UnclaimCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.CreateFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.DeleteFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.SettingCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.management.KickFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.settings.MotdCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.settings.PowerCommand;
import io.github.toberocat.improvedFactions.core.command.sub.faction.settings.RenameCommand;
import io.github.toberocat.improvedFactions.core.command.sub.invite.AcceptInviteCommand;
import io.github.toberocat.improvedFactions.core.command.sub.invite.InviteCommand;
import io.github.toberocat.improvedFactions.core.command.sub.member.JoinFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.member.LeaveFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.utils.BakePermissionsCommand;
import io.github.toberocat.improvedFactions.core.command.sub.utils.ListFactionCommand;
import io.github.toberocat.improvedFactions.core.command.sub.utils.WhoFactionCommand;
import io.github.toberocat.improvedFactions.core.player.FactionPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseCommand extends Command<Command.CommandPacket, Command.ConsoleCommandPacket> {


    public BaseCommand() {

        add(new CreateFactionCommand());
        add(new DeleteFactionCommand());
        add(new ListFactionCommand());
        add(new ClaimCommand());
        add(new UnclaimCommand());
        add(new JoinFactionCommand());
        add(new LeaveFactionCommand());
        add(new InviteCommand());
        add(new AcceptInviteCommand());
        add(new BakePermissionsCommand());
        add(new AdminRoot());
        add(new SettingCommand());
        add(new MotdCommand());
        add(new PowerCommand());
        add(new RenameCommand());
        add(new WhoFactionCommand());
        add(new KickFactionCommand());
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public @NotNull String label() {
        return "base-command";
    }

    @Override
    public @NotNull CommandSettings createSettings() {
        return new CommandSettings(node);
    }

    @Override
    public @NotNull List<String> tabCompletePlayer(@NotNull FactionPlayer<?> player, @NotNull String[] args) {
        return new ArrayList<>(commands.keySet());
    }

    @Override
    public @NotNull List<String> tabCompleteConsole(@NotNull String[] args) {
        return new ArrayList<>(commands.keySet());
    }

    @Override
    public void run(@NotNull Command.CommandPacket packet) {
    }

    @Override
    public void runConsole(@NotNull Command.ConsoleCommandPacket packet) {

    }

    @Override
    public @Nullable Command.CommandPacket createFromArgs(@NotNull FactionPlayer<?> executor,
                                                          @NotNull String[] args) {
        return new Command.CommandPacket() {
        };
    }

    @Override
    public @Nullable Command.ConsoleCommandPacket createFromArgs(@NotNull String[] args) {
        return new Command.ConsoleCommandPacket() {
        };
    }
}
