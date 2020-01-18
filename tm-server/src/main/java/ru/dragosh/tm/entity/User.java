package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.util.ConsoleUtil;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_user")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class User implements Serializable {
    @NotNull
    @Id
    private String id = UUID.randomUUID().toString();
    @Nullable
    @Column(unique = true)
    private String login;
    @Nullable
    private String password;
    @Nullable
    @Enumerated(EnumType.STRING)
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
