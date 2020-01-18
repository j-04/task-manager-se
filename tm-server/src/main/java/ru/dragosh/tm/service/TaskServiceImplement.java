package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.TaskService;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Component
@Scope(scopeName = "singleton")
public final class TaskServiceImplement implements TaskService {
    @NotNull
    @Autowired
    private TaskRepository taskRepository;

    @NotNull
    @Override
    public List<Task> findAll(@NotNull final String userId,
                              @NotNull final String projectId) {
        if (userId == null || userId.isEmpty())
            return Collections.emptyList();
        if (projectId == null || projectId.isEmpty())
            return Collections.emptyList();
        List<Task> list = taskRepository.findAll(userId, projectId);
        return list;
    }

    @Nullable
    @Override
    public Task find(@NotNull final User user,
                     @NotNull final Project project,
                     @NotNull final String nameTask) {
        if (user == null)
            return null;
        if (project == null)
            return null;
        if (nameTask == null || nameTask.isEmpty())
            return null;
        Task task = taskRepository.findByUserAndProjectAndName(user, project, nameTask);
        return task;
    }

    @Override
    public void persist(@NotNull final Task task) throws ParseException {
        if (task == null)
            return;
        taskRepository.save(task);
    }

    @Override
    public void merge(@NotNull final Task task) {
        if (task == null)
            return;
        taskRepository.save(task);
    }

    @Override
    public void remove(@NotNull final User user, @NotNull final String taskId) {
        taskRepository.deleteByUserAndId(user, taskId);
    }

    @Override
    public void removeAll(@NotNull final User user,
                          @NotNull final Project project) {
        if (user == null)
            return;
        if (project == null)
            return;
        taskRepository.deleteAllByUserAndProject(user, project);
    }

    @Override
    public List<Task> getEntitiesList() {
        List<Task> list = taskRepository.findAll();
        return list;
    }

    @Override
    public void loadEntities(@NotNull final List<Task> entities) throws ParseException {
        for (Task task: entities) {
            taskRepository.save(task);
        }
    }

    @NotNull
    @Override
    public List<Task> findByStringPart(@NotNull final User user,
                                       @NotNull final Project project,
                                       @NotNull final String str) {
        if (user == null)
            return Collections.emptyList();
        if (project == null)
            return Collections.emptyList();
        if (str == null || str.isEmpty())
            return Collections.emptyList();
        List<Task> list = taskRepository.findByStringPart(user, project, str);
        return list;
    }

    @NotNull
    @Override
    public List<Task> getSortedBySystemTime(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Task> list = taskRepository.findByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Task> getSortedByDateStart(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Task> list = taskRepository.findByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Task> getSortedByDateFinish(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Task> list = taskRepository.findByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Task> getSortedByStatus(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Task> list = taskRepository.findByUser(user);
        return list;
    }
}