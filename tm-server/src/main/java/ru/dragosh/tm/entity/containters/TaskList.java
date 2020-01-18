package ru.dragosh.tm.entity.containters;

import lombok.Getter;
import lombok.Setter;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.entity.Task;

import java.util.List;

public final class TaskList {
    @Getter
    @Setter
    private List<TaskDTO> taskList;
}
