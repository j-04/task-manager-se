package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.exception.Exception_Exception;

public final class ProjectsSortedByDateStartCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "sort projects by date start";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод проектов отсортированных по дате начала)";
    }

    @Override
    public void execute() throws Exception_Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        projectEndPoint.getSortedByDateStart(session);
    }
}
