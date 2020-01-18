package ru.dragosh.tm.endpoint;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.exception.EntityIsAlreadyExistException;
import ru.dragosh.tm.exception.EntityNotExistsException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserEndPoint {
    @WebMethod
    Session authorisation(SessionDTO session, String login, String password) throws AccessForbiddenException, EntityIsAlreadyExistException, EntityNotExistsException;
    @WebMethod
    Session registration(SessionDTO session, String login, String password) throws AccessForbiddenException, EntityIsAlreadyExistException;
    @WebMethod
    Session updateLogin(SessionDTO session, String newLogin) throws AccessForbiddenException, EntityIsAlreadyExistException;
    @WebMethod
    Session updatePassword(SessionDTO session, String newPassword) throws AccessForbiddenException;
    @WebMethod
    User getProfile(SessionDTO session) throws AccessForbiddenException;
    @WebMethod
    Session logout(SessionDTO session) throws Exception;
}
