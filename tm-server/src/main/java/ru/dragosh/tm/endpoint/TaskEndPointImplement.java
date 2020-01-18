package ru.dragosh.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.entity.containters.TaskList;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.util.DTOUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@WebService(endpointInterface = "ru.dragosh.tm.endpoint.TaskEndPoint")
@Component
@Scope(scopeName = "singleton")
public final class TaskEndPointImplement implements TaskEndPoint {
    @NotNull
    private ServiceLocator serviceLocator;

    public TaskEndPointImplement(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public TaskList findAll(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                            @WebParam(name = "projectId") @Nullable final String projectId) throws Exception{
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (projectId == null)
            throw new AccessForbiddenException();
        if (projectId.isEmpty())
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        List<Task> tasks = serviceLocator.getTaskService().findAll(session.getUserId(), projectId);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }

    @WebMethod
    public Task find(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                     @WebParam(name = "projectId") @Nullable final String projectId,
                     @WebParam(name = "taskName") @Nullable final String taskName) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (projectId == null || projectId.isEmpty())
            throw new AccessForbiddenException();
        if (taskName == null || taskName.isEmpty())
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        Project project = serviceLocator.getProjectService().findById(projectId);
        return serviceLocator.getTaskService().find(user, project, taskName);
    }

    @WebMethod
    public void removeAll(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                          @WebParam(name = "projectId") @Nullable final String projectId) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (projectId == null || projectId.isEmpty())
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        Project project = serviceLocator.getProjectService().findById(projectId);
        serviceLocator.getTaskService().removeAll(user, project);
    }

    @WebMethod
    public TaskList findByStringPart(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                                     @WebParam(name = "projectId") @Nullable final String projectId,
                                     @WebParam(name = "part") @Nullable final String part
    ) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (projectId == null || projectId.isEmpty())
            throw new AccessForbiddenException();
        if (part == null || part.isEmpty())
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        Project project = serviceLocator.getProjectService().findById(projectId);
        List<Task> tasks = serviceLocator.getTaskService().findByStringPart(user, project, part);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }

    @WebMethod
    public void persist(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
            @WebParam(name = "task") @Nullable final Task task) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (task == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        SimpleDateFormat dt = new SimpleDateFormat("dd.mm.yyyy");
        task.setDateStart(dt.format(dt.parse(task.getDateStart())));
        task.setDateFinish(dt.format(dt.parse(task.getDateFinish())));
        serviceLocator.getTaskService().persist(task);

    }

    @WebMethod
    public void merge(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
            @WebParam(name = "task") @Nullable final Task task) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        if (task == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        serviceLocator.getTaskService().merge(task);
    }

    @WebMethod
    public void remove(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                       @WebParam(name = "id") @Nullable final String id
    ) throws Exception {
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        serviceLocator.getTaskService().remove(user, id);
    }

    @WebMethod
    public TaskList getSortedBySystemTime(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Task> tasks = serviceLocator.getTaskService().getSortedBySystemTime(user);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }

    @WebMethod
    public TaskList getSortedByDateStart(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Task> tasks = serviceLocator.getTaskService().getSortedByDateStart(user);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }

    @WebMethod
    public TaskList getSortedByDateFinish(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Task> tasks = serviceLocator.getTaskService().getSortedByDateFinish(user);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }

    @WebMethod
    public TaskList getSortedByStatus(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        TaskList taskList = new TaskList();
        final List<TaskDTO> taskDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Task> tasks = serviceLocator.getTaskService().getSortedByStatus(user);
        tasks.forEach(task -> taskDTOList.add(DTOUtil.taskToTaskDTO(task)));
        taskList.setTaskList(taskDTOList);
        return taskList;
    }
}
