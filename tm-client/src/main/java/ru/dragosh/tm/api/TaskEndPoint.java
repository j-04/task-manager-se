package ru.dragosh.tm.api;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.dto.containers.TaskList;
import ru.dragosh.tm.exception.Exception_Exception;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "TaskEndPoint", targetNamespace = "http://endpoint.tm.dragosh.ru/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TaskEndPoint {
    @WebMethod
    TaskList findAll(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                     @WebParam(name = "session", partName = "arg1") String projectId);
    @WebMethod
    TaskDTO find(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                 @WebParam(name = "projectId", partName = "arg1") String projectId,
                 @WebParam(name = "taskName", partName = "arg2")String taskName) throws Exception_Exception;
    @WebMethod
    void removeAll(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                   @WebParam(name = "projectId", partName = "arg1")String projectId);
    @WebMethod
    TaskList findByStringPart(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                              @WebParam(name = "projectId", partName = "arg1")String projectId,
                              @WebParam(name = "part", partName = "arg2")String part);
    @WebMethod
    void persist(@WebParam(name = "task", partName = "arg0") TaskDTO Task) throws Exception_Exception;
    @WebMethod
    void merge(@WebParam(name = "task", partName = "arg0") TaskDTO task);
    @WebMethod
    void remove(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                @WebParam(name = "taskId", partName = "arg0") String taskId);
    @WebMethod
    TaskList getSortedBySystemTime(@WebParam(name = "session", partName = "arg0") SessionDTO session);
    @WebMethod
    TaskList getSortedByDateStart(@WebParam(name = "session", partName = "arg0") SessionDTO session);
    @WebMethod
    TaskList getSortedByDateFinish(@WebParam(name = "session", partName = "arg0") SessionDTO session);
    @WebMethod
    TaskList getSortedByStatus(@WebParam(name = "session", partName = "arg0") SessionDTO session);
}
