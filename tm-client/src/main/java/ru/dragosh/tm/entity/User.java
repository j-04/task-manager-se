package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumeration.RoleType;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class User implements Serializable {
    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String login;
    @Nullable
    private String password;
    @Nullable
    private RoleType role = RoleType.USER;

    public User(@Nullable final String login,
                @Nullable final String password,
                @Nullable final RoleType role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @NotNull
    @Override
    public String toString() {
        return "UUID: " + this.id + ";\n" +
                "Login: " + this.login + ";\n" +
                "Password: " + this.password + ";\n" +
                "Role: " + this.role + ";\n";
    }
}
