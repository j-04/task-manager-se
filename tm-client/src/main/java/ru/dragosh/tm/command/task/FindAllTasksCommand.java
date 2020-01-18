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

import static ru.dragosh.tm.util.ConsoleUtil.log;

public final class FindAllTasksCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "find all tasks";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(находит и выводит на экран все задачи по определенному проекту из базы данных Tasks)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        TaskEndPointService taskEndPointService = serviceLocator.getTaskEndPointService();
        TaskEndPoint taskEndPoint = taskEndPointService.getTaskEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        @Nullable String projectName = readWord("Введите название проекта: ");
        if (projectName == null || projectName.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        ProjectDTO project = null;
        project = projectEndPoint.find(projectName, session);
        if (project == null) {
            ConsoleUtil.log(MessageType.PROJECT_NOT_FOUND);
            return;
        }
        taskEndPoint.findAll(session, project.getId()).getTaskList().forEach(ConsoleUtil::taskOutput);
    }
}