package ru.dragosh.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.enumeration.Status;

import java.util.UUID;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TaskDTO {
    @NotNull
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    @Nullable
    @Getter
    @Setter
    private String name;
    @Nullable
    @Getter
    @Setter
    private String description;
    @Nullable
    @Getter
    @Setter
    private String dateStart;
    @Nullable
    @Getter
    @Setter
    private String dateFinish;
    @Nullable
    @Getter
    @Setter
    private Project project;
    @Nullable
    @Getter
    @Setter
    private User user;
    @NotNull
    @Getter
    @Setter
    private Status status = Status.SCHEDULED;
    @Nullable
    @Getter
    @Setter
    private Long systemTime = System.currentTimeMillis();

    public TaskDTO(@Nullable final String name,
                @Nullable final String description,
                @Nullable final String dateStart,
                @Nullable final String dateFinish,
                @Nullable final Project project,
                @Nullable final User user) {
        this.name = name;
        this.description = description;
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.project = project;
        this.user = user;
    }
}
