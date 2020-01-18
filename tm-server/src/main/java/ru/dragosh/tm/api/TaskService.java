package ru.dragosh.tm.api;

import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    List<Task> findAll(String userId, String projectId);
    Task find(User user, Project project, String nameTask);
    void persist(Task task) throws ParseException;
    void merge(Task task);
    void remove(User user, String taskId);
    void removeAll(User user, Project project);

    List<Task> getEntitiesList();
    void loadEntities(List<Task> entities) throws ParseException;

    List<Task> findByStringPart(User user, Project project, String str);

    List<Task> getSortedBySystemTime(User user);
    List<Task> getSortedByDateStart(User user);
    List<Task> getSortedByDateFinish(User user);
    List<Task> getSortedByStatus(User user);
}