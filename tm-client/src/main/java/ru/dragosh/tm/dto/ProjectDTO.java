package ru.dragosh.tm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.enumeration.Status;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ProjectDTO {
    @NotNull
    @Getter
    @Setter
    private String id = UUID.randomUUID().toString();
    @NotNull
    @Getter
    @Setter
    private String name;
    @NotNull
    @Getter
    @Setter
    private String description;
    @NotNull
    @Getter
    @Setter
    private String dateStart;
    @NotNull
    @Getter
    @Setter
    private String dateFinish;
    @NotNull
    @Getter
    @Setter
    private User user;
    @NotNull
    @Getter
    @Setter
    private List<Task> taskList;
    @NotNull
    @Getter
    @Setter
    private Status status = Status.SCHEDULED;
    @NotNull
    @Getter
    @Setter
    private Long systemTime = System.currentTimeMillis();

    public ProjectDTO(@NotNull final String name,
                   @NotNull final String description,
                   @NotNull final String dateStart,
                   @NotNull final String dateFinish,
                   @NotNull final User user) {
        this.name = name;
        this.description = description;
        this.setDateStart(dateStart);
        this.setDateFinish(dateFinish);
        this.user = user;
    }
}
