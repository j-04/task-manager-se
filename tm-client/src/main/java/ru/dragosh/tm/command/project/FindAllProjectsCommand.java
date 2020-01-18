package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.util.ConsoleUtil;

import java.util.HashSet;
import java.util.Set;

public final class FindAllProjectsCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "find all projects";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(находит и выводит на экран все проекты из базы данных Projects)";
    }

    @Override
    public void execute() {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        projectEndPoint.findAll(serviceLocator.getCurrentSession()).getProjectList().forEach(ConsoleUtil::projectOutput);
    }
}