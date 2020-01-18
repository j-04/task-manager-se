package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.exception.Exception_Exception;

public final class ProjectsSortedBySystemTimeCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "sort projects by date finish";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод проектов отсортированных по дате окончания)";
    }

    @Override
    public void execute() throws Exception_Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        projectEndPoint.getSortedBySystemTime(session);
    }
}
