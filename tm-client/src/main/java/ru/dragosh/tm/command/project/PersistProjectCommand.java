package ru.dragosh.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.api.ProjectEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.ProjectEndPointService;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class PersistProjectCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "add project";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(добавляет в Project новый проект)";
    }

    @Override
    public void execute() throws Exception {
        ProjectEndPointService projectEndPointService = serviceLocator.getProjectEndPointService();
        ProjectEndPoint projectEndPoint = projectEndPointService.getProjectEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        @Nullable String projectName = readWord("Введите название проекта: ");
        @Nullable String projectDescription = readWord("Введите описание проекта: ");
        @Nullable String projectDateStart = readWord("Введите дату начала проекта: ");
        @Nullable String projectDateFinish = readWord("Введите дату окончания проекта: ");

        if (projectName == null || projectName.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (projectDescription == null || projectDescription.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (projectDateStart == null || projectDateStart.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (projectDateFinish == null || projectDateFinish.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }
        if (projectEndPoint.find(projectName, session) != null) {
            ConsoleUtil.log(MessageType.PROJECT_EXISTS);
            return;
        }
        ProjectDTO project = new ProjectDTO();
        project.setUser(new User());
        project.setName(projectName);
        project.setDescription(projectDescription);
        projectEndPoint.persist(session, project);
        ConsoleUtil.log(MessageType.WRONG_DATE_FORMAT);
    }
}