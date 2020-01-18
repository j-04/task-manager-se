package ru.dragosh.tm.util;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.dto.UserDTO;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;

public class DTOUtil {
    public static ProjectDTO projectToProjectDTO(@NotNull final Project project) {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO(
                project.getName(),
                project.getDescription(),
                project.getDateStart(),
                project.getDateFinish(),
                project.getUser()
        );
        projectDTO.setId(project.getId());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setSystemTime(project.getSystemTime());
        return projectDTO;
    }

    public static TaskDTO taskToTaskDTO(@NotNull final Task task) {
        @NotNull final TaskDTO taskDTO = new TaskDTO(
                task.getName(),
                task.getDescription(),
                task.getDateStart(),
                task.getDateFinish(),
                task.getProject(),
                task.getUser()
        );
        taskDTO.setId(task.getId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setSystemTime(task.getSystemTime());
        return taskDTO;
    }

    public static UserDTO userToUserDTO(@NotNull final User user) {
        @NotNull final UserDTO userDTO = new UserDTO(
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        );
        userDTO.setId(user.getId());
        return userDTO;
    }

    public static SessionDTO sessionToSessionDTO(@NotNull final Session session) {
        @NotNull final SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setSignature(session.getSignature());
        sessionDTO.setTimeStamp(session.getTimeStamp());
        sessionDTO.setUserId(session.getUserId());
        return sessionDTO;
    }

    public static Session sessionDTOToSession(@NotNull final SessionDTO sessionDTO) {
        @NotNull final Session session = new Session();
        session.setId(sessionDTO.getId());
        session.setSignature(sessionDTO.getSignature());
        session.setTimeStamp(sessionDTO.getTimeStamp());
        session.setUserId(sessionDTO.getUserId());
        return session;
    }
}
