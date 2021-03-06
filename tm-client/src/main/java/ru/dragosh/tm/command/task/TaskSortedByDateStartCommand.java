package ru.dragosh.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.TaskEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.TaskEndPointService;

public final class TaskSortedByDateStartCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "sort tasks by date start";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод задач отсортированных по дате начала)";
    }

    @Override
    public void execute() {
        TaskEndPointService taskEndPointService = serviceLocator.getTaskEndPointService();
        TaskEndPoint taskEndPoint = taskEndPointService.getTaskEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        taskEndPoint.getSortedByDateStart(session);
    }
}
