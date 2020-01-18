package ru.dragosh.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.api.TaskEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.endpoint.service.TaskEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class TaskByNameDescriptionPartCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "find task by name part";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(поиск задач по части названия)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        TaskEndPointService taskEndPointService = serviceLocator.getTaskEndPointService();
        TaskEndPoint taskEndPoint = taskEndPointService.getTaskEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        @Nullable String projectName = readWord("Введите название проекта: ");
        @Nullable String taskNameOrDescriptionPart = readWord("Введите название или описание задачи: ");
        if (projectName == null || projectName.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskNameOrDescriptionPart == null || taskNameOrDescriptionPart.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        @Nullable ProjectDTO project = null;
        project = projectEndPoint.find(projectName, session);
        if (project == null) {
            ConsoleUtil.log(MessageType.PROJECT_NOT_FOUND);
            return;
        }
        taskEndPoint.findByStringPart(session, project.getId(), taskNameOrDescriptionPart).getTaskList().forEach(ConsoleUtil::taskOutput);
    }
}
