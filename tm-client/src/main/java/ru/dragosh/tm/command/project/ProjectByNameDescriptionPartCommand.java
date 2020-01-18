package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.exception.Exception_Exception;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class ProjectByNameDescriptionPartCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "find project by name part";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(поиск проектов по части названия)";
    }

    @Override
    public void execute() throws Exception_Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        String projectNameOrDescriptionPart = readWord("Введите название или описание проекта: ");
        if (projectNameOrDescriptionPart == null || projectNameOrDescriptionPart.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        projectEndPoint.findByStringPart(session, projectNameOrDescriptionPart).getProjectList().forEach(ConsoleUtil::projectOutput);
    }
}
