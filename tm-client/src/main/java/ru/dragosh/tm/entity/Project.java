package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.enumeration.Status;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Project implements Entity, Serializable {
    @NotNull
    private String id = UUID.randomUUID().toString();
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String dateStart;
    @NotNull
    private String dateFinish;
    @NotNull
    private User user;
    @NotNull
    private List<Task> taskList;
    @NotNull
    private Status status = Status.SCHEDULED;
    @NotNull
    private Long systemTime = System.currentTimeMillis();

    public Project(@NotNull final String name,
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

    @NotNull
    @Override
    public String toString() {
        return "UUID проекта: " + this.id + ";\n" +
                "Название проекта: " + this.name + ";\n" +
                "Описание проекта: " + this.description + ";\n" +
                "Дата начала проекта: " + this.dateStart + ";\n" +
                "Дата окончания проекта: " + this.dateFinish + ";\n" +
                "Статус: " + this.status + ";";
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        if (id == null || name == null)
            return false;

        return name.equals(project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateStart, dateFinish);
    }
}