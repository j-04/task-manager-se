package ru.dragosh.tm.endpoint;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.containters.TaskList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.text.ParseException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TaskEndPoint {
    @WebMethod
    TaskList findAll(SessionDTO sessionDTO, String projectId) throws Exception;
    @WebMethod
    Task find(SessionDTO sessionDTO, String projectId, String taskName) throws Exception;
    @WebMethod
    void removeAll(SessionDTO sessionDTO, String projectId) throws Exception;
    @WebMethod
    TaskList findByStringPart(SessionDTO sessionDTO, String projectId, String part) throws Exception;
    @WebMethod
    void persist(SessionDTO sessionDTO, Task Task) throws ParseException, Exception;
    @WebMethod
    void merge(SessionDTO sessionDTO, Task task) throws Exception;
    @WebMethod
    void remove(SessionDTO sessionDTO, String taskId) throws Exception;
    @WebMethod
    TaskList getSortedBySystemTime(SessionDTO sessionDTO) throws Exception;
    @WebMethod
    TaskList getSortedByDateStart(SessionDTO sessionDTO) throws Exception;
    @WebMethod
    TaskList getSortedByDateFinish(SessionDTO sessionDTO) throws Exception;
    @WebMethod
    TaskList getSortedByStatus(SessionDTO sessionDTO) throws Exception;
}
