package ru.dragosh.tm.enumeration;

import org.jetbrains.annotations.NotNull;

public enum Status {
    SCHEDULED("Запланировано"),
    PROCESSING("В процессе"),
    FINISHED("Завершено");

    private String displayName;

    Status(@NotNull final String displayName) {
        this.displayName = displayName;
    }

    public String getStatusName() {
        return displayName;
    }
}
