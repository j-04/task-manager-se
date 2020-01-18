package ru.dragosh.tm.api;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ProjectService {
    List<Project> findAll(User user);
    Project find(String projectName, User user);
    void persist(Project project) throws ParseException;
    void merge(Project project);
    void remove(User user, String projectId);
    void removeAll(User user);

    Project findById(String id);
    List<Project> getEntitiesList();
    void loadEntities(List<Project> entities) throws ParseException;

    List<Project> findByStringPart(User user, String str);

    List<Project> getSortedBySystemTime(User user);
    List<Project> getSortedByDateStart(User user);
    List<Project> getSortedByDateFinish(User user);
    List<Project> getSortedByStatus(User user);
}