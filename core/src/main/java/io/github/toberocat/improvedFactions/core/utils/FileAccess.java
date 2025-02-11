package io.github.toberocat.improvedFactions.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public final class FileAccess {
    public static final String FACTION_FOLDER = "factions";
    public static final String PERSISTENT_FOLDER = "persistent";
    public static final String CHUNKS_FOLDER = "chunks";
    public static final String PLAYERS_FOLDER = "players";
    public static final String MESSAGES_FOLDER = "messages";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final @NotNull File parent;

    public FileAccess(@NotNull File parent, @NotNull String... relativePath) {
        this.parent = new File(parent, String.join(File.separator, relativePath));

        if (!parent.exists()) parent.mkdirs();
    }

    public void delete(String... relativePath) {
        getFile(relativePath).delete();
    }

    public boolean has(String... relativePath) {
        return getFile(relativePath).exists();
    }

    public <T> void write(@NotNull T object, String... relativePath) throws IOException {
        MAPPER.writeValue(getFile(relativePath), object);
    }

    public <T> T read(@NotNull Class<T> clazz, String... relativePath) throws IOException {
        return MAPPER.readValue(getFile(relativePath), clazz);
    }

    public File[] listFiles(String... relativePath) {
        return getFile(relativePath).listFiles();
    }

    public String[] list(String... relativePath) {
        return getFile(relativePath).list();
    }

    public @NotNull File getFile(String... relativePath) {
        return new File(parent, String.join(File.separator, relativePath));
    }
}
