package io.github.toberocat.improvedFactions.core.translator.layout.messages;

import io.github.toberocat.improvedFactions.core.translator.layout.messages.faction.FactionMessage;

import java.util.HashMap;

public class Messages {
    private FactionMessage faction;
    private HashMap<String, HashMap<String, String>> command;

    public Messages() {
    }

    public FactionMessage getFaction() {
        return faction;
    }

    public void setFaction(FactionMessage faction) {
        this.faction = faction;
    }

    public HashMap<String, HashMap<String, String>> getCommand() {
        return command;
    }

    public void setCommand(HashMap<String, HashMap<String, String>> command) {
        this.command = command;
    }
}
