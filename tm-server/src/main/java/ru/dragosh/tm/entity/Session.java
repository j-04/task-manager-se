package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Session implements Cloneable {
    @Override
    public Session clone() {
        try {
            return (Session) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private Long timeStamp = 0L;
    @Nullable
    private String userId = "";
    @Nullable
    private String signature = "";

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, "ATOM");
    }
}
