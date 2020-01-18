package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.exception.Exception_Exception;

public final class RemoveAllProjectsCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "remove all projects";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(удаляет все проекты и связанные с ними задачи из баз данных Projects и Tasks)";
    }

    @Override
    public void execute() throws Exception_Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        projectEndPoint.removeAll(session);
    }
}