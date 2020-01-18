package ru.dragosh.tm.api;

import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.containers.ProjectList;
import ru.dragosh.tm.exception.Exception_Exception;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "ProjectEndPoint", targetNamespace = "http://endpoint.tm.dragosh.ru/")
public interface ProjectEndPoint {
    @WebMethod
    ProjectList findAll(@WebParam(name = "session", partName = "arg0") SessionDTO session);

    @WebMethod
    ProjectDTO find(@WebParam(name = "projectName", partName = "arg0") String projectName,
                    @WebParam(name = "session", partName = "arg1") SessionDTO session) throws Exception_Exception;

    @WebMethod
    void persist(
            @WebParam(name = "session", partName = "arg0") SessionDTO session,
            @WebParam(name = "project", partName = "arg1") ProjectDTO project) throws Exception_Exception;

    @WebMethod
    void merge(
            @WebParam(name = "session", partName = "arg0") SessionDTO session,
            @WebParam(name = "project", partName = "arg1") ProjectDTO project) throws Exception_Exception;

    @WebMethod
    void remove(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                @WebParam(name = "projectId", partName = "arg1") String projectId) throws Exception_Exception;

    @WebMethod
    void removeAll(@WebParam(name = "session", partName = "arg0") SessionDTO session) throws Exception_Exception;

    @WebMethod
    ProjectList findByStringPart(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                                 @WebParam(name = "str", partName = "arg1") String str) throws Exception_Exception;

    @WebMethod
    ProjectList getSortedBySystemTime(@WebParam(name = "session", partName = "arg0") SessionDTO session) throws Exception_Exception;

    @WebMethod
    ProjectList getSortedByDateStart(@WebParam(name = "session", partName = "arg0") SessionDTO session) throws Exception_Exception;

    @WebMethod
    ProjectList getSortedByDateFinish(@WebParam(name = "session", partName = "arg0") SessionDTO session) throws Exception_Exception;

    @WebMethod
    ProjectList getSortedByStatus(@WebParam(name = "session", partName = "arg0") SessionDTO session) throws Exception_Exception;
}
