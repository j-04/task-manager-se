package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class FindProjectCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "find project";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(находит и выводит на экран проект из базы данных Projects)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        String projectName = readWord("Введите название проекта: ");
        if (projectName == null) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (projectName.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        projectEndPoint.find(projectName, serviceLocator.getCurrentSession());
    }
}

