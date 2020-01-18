package ru.dragosh.tm.enumeration;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public enum Status {
    SCHEDULED("Запланировано"),
    PROCESSING("В процессе"),
    FINISHED("Завершено");
    private String id = UUID.randomUUID().toString();
    private String displayName;

    Status(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getStatusName() {
        return displayName;
    }
}
