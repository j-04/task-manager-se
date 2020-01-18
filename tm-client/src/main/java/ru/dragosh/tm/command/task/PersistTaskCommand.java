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
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

import static ru.dragosh.tm.util.ConsoleUtil.log;

public final class PersistTaskCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "add task";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(добавляет в проект новую задачу)";
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
        @Nullable String taskDescription = readWord("Введите описание задачи: ");
        @Nullable String taskDateStart = readWord("Введите дату начала выполнения задачи: ");
        @Nullable String taskDateFinish = readWord("Введите дату окончания выполнения задачи: ");

        if (projectName == null || projectName.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskName == null || taskName.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskDescription == null || taskDescription.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskDateStart == null || taskDateStart.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (taskDateFinish == null || taskDateFinish.isEmpty()) {
            log(MessageType.WRONG_DATA_FORMAT);
            return;
        }

        @Nullable ProjectDTO project = null;
        project = projectEndPoint.find(projectName, session);
        if (project == null) {
            ConsoleUtil.log(MessageType.PROJECT_NOT_FOUND);
            return;
        }
        if (taskEndPoint.find(session, project.getId(), taskName) != null) {
            ConsoleUtil.log(MessageType.TASK_EXISTS);
            return;
        }

        TaskDTO task = new TaskDTO();
        task.setName(taskName);
        task.setDescription(taskDescription);
        task.setDateStart(taskDateStart);
        task.setDateFinish(taskDateFinish);
        task.setProject(new Project());
        task.setUser(new User());
        taskEndPoint.persist(task);
    }
}