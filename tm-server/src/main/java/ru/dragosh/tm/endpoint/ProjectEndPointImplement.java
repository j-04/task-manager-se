package ru.dragosh.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.entity.containters.ProjectList;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.util.DTOUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@WebService
@Component
@Scope(scopeName = "singleton")
public final class ProjectEndPointImplement implements ProjectEndPoint {
    @NotNull
    private ServiceLocator serviceLocator;

    public ProjectEndPointImplement(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public ProjectList findAll(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (sessionDTO == null)
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().findAll(user);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }

    @WebMethod
    public Project find(@WebParam(name = "projectName") @Nullable final String projectName,
                        @WebParam(name = "session") @Nullable final SessionDTO sessionDTO
    ) throws Exception {
        if (projectName == null)
            return null;
        if (sessionDTO == null)
            return null;
        if (projectName.isEmpty())
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        return serviceLocator.getProjectService().find(projectName, user);
    }

    @WebMethod
    public void persist(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
            @WebParam(name = "project") @Nullable final Project project) throws Exception {
        if (project == null)
            return;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        SimpleDateFormat dt = new SimpleDateFormat("dd.mm.yyyy");
        project.setDateStart(dt.format(dt.parse(project.getDateStart())));
        project.setDateFinish(dt.format(dt.parse(project.getDateFinish())));
        serviceLocator.getProjectService().persist(project);
    }

    @WebMethod
    public void merge(
            @WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
            @WebParam(name = "project") @Nullable final Project project) throws Exception {
        if (project == null)
            return;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        serviceLocator.getProjectService().merge(project);
    }

    @WebMethod
    public void remove(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                       @WebParam(name = "projectId") @Nullable final String projectId
    ) throws Exception {
        if (sessionDTO == null)
            return;
        if (projectId == null)
            return;
        if (projectId.isEmpty())
            return;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        serviceLocator.getProjectService().remove(user, projectId);
    }

    @WebMethod
    public void removeAll(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            return;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        User user = serviceLocator.getUserService().findById(session.getId());
        serviceLocator.getProjectService().removeAll(user);
    }

    @WebMethod
    public ProjectList findByStringPart(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                                        @WebParam(name = "str") @Nullable final String str
    ) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (str == null)
            return projectList;
        if (str.isEmpty())
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().findByStringPart(user, str);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }

    @WebMethod
    public ProjectList getSortedBySystemTime(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (sessionDTO == null)
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().getSortedBySystemTime(user);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }

    @WebMethod
    public ProjectList getSortedByDateStart(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (sessionDTO == null)
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().getSortedBySystemTime(user);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }

    @WebMethod
    public ProjectList getSortedByDateFinish(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (sessionDTO == null)
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().getSortedBySystemTime(user);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }

    @WebMethod
    public ProjectList getSortedByStatus(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        ProjectList projectList = new ProjectList();
        projectList.setProjectList(Collections.emptyList());
        if (sessionDTO == null)
            return projectList;
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validateRole(session, RoleType.USER, RoleType.ADMIN);
        final List<ProjectDTO> projectDTOList = new ArrayList<>();
        User user = serviceLocator.getUserService().findById(session.getId());
        List<Project> projects = serviceLocator.getProjectService().getSortedBySystemTime(user);
        projects.forEach(project -> projectDTOList.add(DTOUtil.projectToProjectDTO(project)));
        projectList.setProjectList(projectDTOList);
        return projectList;
    }
}