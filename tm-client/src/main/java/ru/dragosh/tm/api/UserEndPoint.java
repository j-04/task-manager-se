package ru.dragosh.tm.api;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.dto.UserDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "UserEndPoint", targetNamespace = "http://endpoint.tm.dragosh.ru/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserEndPoint {
    @WebMethod
    SessionDTO authorisation(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                             @WebParam(name = "login", partName = "arg1") String login,
                             @WebParam(name = "password", partName = "arg2") String password);
    @WebMethod
    SessionDTO registration(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                         @WebParam(name = "login", partName = "arg1") String login,
                         @WebParam(name = "password", partName = "arg3") String password);
    @WebMethod
    SessionDTO updateLogin(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                        @WebParam(name = "newLogin", partName = "arg1")String newLogin);
    @WebMethod
    SessionDTO updatePassword(@WebParam(name = "session", partName = "arg0") SessionDTO session,
                           @WebParam(name = "newPassword", partName = "arg1")String newPassword);
    @WebMethod
    UserDTO getProfile(@WebParam(name = "session", partName = "arg0") SessionDTO session);
    @WebMethod
    SessionDTO logout(@WebParam(name = "session", partName = "arg0") SessionDTO session);
}
