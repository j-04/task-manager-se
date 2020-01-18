package ru.dragosh.tm.enumeration;

import org.jetbrains.annotations.NotNull;

public enum RoleType {
    USER("43d97552-0b01-11ea-8d71-362b9e155667", "USER"),
    ADMIN("43d97a20-0b01-11ea-8d71-362b9e155667", "ADMIN");
    @NotNull
    private String id;
    @NotNull
    private String roleName;

    RoleType(@NotNull final String id,
                   @NotNull final String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getRoleName() {
        return roleName;
    }
}
