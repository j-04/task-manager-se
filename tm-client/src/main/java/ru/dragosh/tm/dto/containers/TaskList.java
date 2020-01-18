package ru.dragosh.tm.dto.containers;

import lombok.Getter;
import lombok.Setter;
import ru.dragosh.tm.dto.TaskDTO;

import java.util.List;

public final class TaskList {
    @Getter
    @Setter
    private List<TaskDTO> taskList;
}
