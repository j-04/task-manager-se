package ru.dragosh.tm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SignatureUtil {
    @Nullable
    public static String sign(
            @Nullable final Object value,
            @Nullable final String salt,
            @Nullable final Integer cycle
    ) {
        try {
            @NotNull final ObjectMapper objectMapper =
                    new ObjectMapper();
            @NotNull final String json =
                    objectMapper.writeValueAsString(value);
            return sign(json, salt, cycle);
        } catch (final JsonProcessingException e) {
            return null;
        }
    }

    public static String sign(
            @NotNull final String value,
            @NotNull final String salt,
            @NotNull final Integer cycle
    ) {
        if (value == null || salt == null || cycle == null)
            return null;
        @NotNull String result = value;
        for (int i = 0; i < cycle; i++) {
            result = ConsoleUtil.getHash(salt + value + salt);
        }
        return result;
    }
}
