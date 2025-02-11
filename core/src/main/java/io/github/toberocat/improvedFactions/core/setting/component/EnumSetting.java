package io.github.toberocat.improvedFactions.core.setting.component;

import io.github.toberocat.improvedFactions.core.exceptions.setting.ErrorParsingSettingException;
import io.github.toberocat.improvedFactions.core.setting.Setting;
import org.jetbrains.annotations.NotNull;

public class EnumSetting<E extends Enum<E>> extends Setting<E> {

    private final String label;

    public EnumSetting(String label) {
        this.label = label;
    }

    @Override
    public @NotNull E createFromSave(@NotNull String saved) throws ErrorParsingSettingException {
        String[] split = saved.split(":");
        String clazzString = split[0];
        int ordinal = Integer.parseInt(split[1]);

        try {
            Class<?> clazz = Class.forName(clazzString);
            return (E) clazz.getEnumConstants()[ordinal];
        } catch (ClassNotFoundException e) {
            throw new ErrorParsingSettingException();
        }
    }

    @Override
    public @NotNull String toSave(E value) {
        return value.getClass().getName() + ":" + value.ordinal();
    }

    @Override
    public @NotNull String label() {
        return label;
    }
}
