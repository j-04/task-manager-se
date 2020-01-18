package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class RemoveProjectCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "remove project";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(удаляет из Project base проект)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        @Nullable String projectName = readWord("Введите название проекта: ");
        if (projectName == null || projectName.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        @Nullable ProjectDTO project = null;
        project = projectEndPoint.find(projectName, session);
        if (project == null) {
            ConsoleUtil.log(MessageType.PROJECT_NOT_FOUND);
            return;
        }
        projectEndPoint.remove(session, project.getId());
    }
}