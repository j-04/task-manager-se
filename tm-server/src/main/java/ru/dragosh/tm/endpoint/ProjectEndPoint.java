package ru.dragosh.tm.endpoint;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.containters.ProjectList;
import ru.dragosh.tm.exception.AccessForbiddenException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ProjectEndPoint {
    @WebMethod
    ProjectList findAll(SessionDTO sessionDTO) throws AccessForbiddenException, Exception;

    @WebMethod
    Project find(String projectName, SessionDTO sessionDTO) throws Exception;

    @WebMethod
    void persist(SessionDTO sessionDTO, Project project) throws Exception;

    @WebMethod
    void merge(SessionDTO sessionDTO, Project project) throws Exception;

    @WebMethod
    void remove(SessionDTO sessionDTO, String projectId) throws Exception;

    @WebMethod
    void removeAll(SessionDTO sessionDTO) throws Exception;

    @WebMethod
    ProjectList findByStringPart(SessionDTO sessionDTO, String str) throws Exception;

    @WebMethod
    ProjectList getSortedBySystemTime(SessionDTO sessionDTO) throws Exception;

    @WebMethod
    ProjectList getSortedByDateStart(SessionDTO sessionDTO) throws Exception;

    @WebMethod
    ProjectList getSortedByDateFinish(SessionDTO sessionDTO) throws Exception;

    @WebMethod
    ProjectList getSortedByStatus(SessionDTO sessionDTO) throws Exception;
}
