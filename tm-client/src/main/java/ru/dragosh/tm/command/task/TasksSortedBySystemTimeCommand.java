package ru.dragosh.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.TaskEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.TaskEndPointService;

public final class TasksSortedBySystemTimeCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "sort tasks by system time";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод задач отсортированных по порядку создания в системе)";
    }

    @Override
    public void execute() {
        TaskEndPointService taskEndPointService = serviceLocator.getTaskEndPointService();
        TaskEndPoint taskEndPoint = taskEndPointService.getTaskEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        taskEndPoint.getSortedBySystemTime(session);
    }
}
