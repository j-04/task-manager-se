package ru.dragosh.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.api.TaskEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.endpoint.service.TaskEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

import static ru.dragosh.tm.util.ConsoleUtil.log;

public final class RemoveTaskCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "remove task";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(удаляет из проекта задачу)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        TaskEndPointService taskEndPointService = serviceLocator.getTaskEndPointService();
        TaskEndPoint taskEndPoint = taskEndPointService.getTaskEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        @Nullable String projectName = readWord("Введите название проекта: ");
        @Nullable String taskName = readWord("Введите название задачи: ");

        if (projectName == null || projectName.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskName == null || taskName.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        @Nullable ProjectDTO project = projectEndPoint.find(projectName, session);
        if (project == null) {
            ConsoleUtil.log(MessageType.PROJECT_NOT_FOUND);
            return;
        }
        @Nullable TaskDTO task = taskEndPoint.find(session, project.getId(), taskName);
        if (task == null) {
            ConsoleUtil.log(MessageType.TASK_NOT_FOUND);
            return;
        }
        taskEndPoint.remove(session, task.getId());
    }
}