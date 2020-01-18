package ru.dragosh.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SessionDTO {
    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private Long timeStamp = 0L;
    @Nullable
    private String userId = "";
    @Nullable
    private String signature = "";
}
