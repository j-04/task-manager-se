package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ProjectService;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.repository.ProjectRepository;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Component
@Scope(scopeName = "singleton")
public final class ProjectServiceImplement implements ProjectService {
    @NotNull
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void persist(@NotNull final Project entity) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("dd.mm.yyyy");
        entity.setDateStart(dt.format(dt.parse(entity.getDateStart())));
        entity.setDateFinish(dt.format(dt.parse(entity.getDateFinish())));
        projectRepository.save(entity);
    }

    @Override
    public void merge(@NotNull final Project entity) {
        projectRepository.save(entity);
    }

    @Override
    public void remove(@NotNull final User user,
                       @NotNull final String entityId) {
        projectRepository.deleteByUserAndId(user, entityId);
    }

    @NotNull
    @Override
    public List<Project> getEntitiesList() {
        List<Project> list = projectRepository.findAll();
        return list;
    }

    @Override
    public void loadEntities(@NotNull final List<Project> entities) throws ParseException {
        for (Project project: entities) {
            projectRepository.save(project);
        }
    }

    @NotNull
    @Override
    public List<Project> findAll(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Project> list = projectRepository.findAllByUser(user);
        return list;
    }

    @Nullable
    @Override
    public Project find(@NotNull final String projectName,
                        @NotNull final User user) {
        if (projectName == null || projectName.isEmpty())
            return null;
        if (user == null)
            return null;
        Project project = projectRepository.findByNameAndUser(projectName, user);
        return project;
    }

    @Override
    public Project findById(@NotNull final String id) {
        if (id == null || id.isEmpty())
            return null;
        Project project = projectRepository.getOne(id);
        return project;
    }

    @Override
    public void removeAll(@NotNull final User user) {
        if (user == null)
            return;
        projectRepository.deleteByUser(user);
    }

    @NotNull
    @Override
    public List<Project> findByStringPart(@NotNull final User user,
                                          @NotNull final String str) {
        if (user == null)
            return Collections.emptyList();
        if (str == null || str.isEmpty())
            return Collections.emptyList();
        List<Project> list = projectRepository.findByStringPart(user, str);
        return list;
    }

    @NotNull
    @Override
    public List<Project> getSortedBySystemTime(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Project> list =  projectRepository.findAllByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Project> getSortedByDateStart(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Project> list =  projectRepository.findAllByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Project> getSortedByDateFinish(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Project> list = projectRepository.findAllByUser(user);
        return list;
    }

    @NotNull
    @Override
    public List<Project> getSortedByStatus(@NotNull final User user) {
        if (user == null)
            return Collections.emptyList();
        List<Project> list = projectRepository.findAllByUser(user);
        return list;
    }
}