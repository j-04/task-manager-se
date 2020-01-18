package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Task implements Entity, Serializable {
    @NotNull
    private String id = UUID.randomUUID().toString();
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String dateStart;
    @Nullable
    private String dateFinish;
    @Nullable
    private Project project;
    @Nullable
    private User user;
    @NotNull
    private Status status = Status.SCHEDULED;
    @Nullable
    private Long systemTime = System.currentTimeMillis();

    public Task(@Nullable final String name,
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

    @NotNull
    @Override
    public String toString() {
        return "UUID задачи: " + this.id + ";\n" +
                "Название задачи: " + this.name + ";\n" +
                "Описание задачи: " + this.description + ";\n" +
                "Дата начала выполнения задачи: " + this.dateStart + ";\n" +
                "Дата окончания выполнения задачи: " + this.dateFinish + ";" +
                "Статус: " + this.status + ";";
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;

        if (name == null)
            return false;

        return name.equals(task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}