package ru.dragosh.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumeration.RoleType;

import java.util.UUID;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class UserDTO {
    @NotNull
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    @Nullable
    @Getter
    @Setter
    private String login;
    @Nullable
    @Getter
    @Setter
    private String password;
    @Nullable
    @Getter
    @Setter
    private RoleType role;

    public UserDTO(@Nullable final String login,
                @Nullable final String password,
                @Nullable final RoleType role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
