package ru.dragosh.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.api.UserService;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.exception.EntityIsAlreadyExistException;
import ru.dragosh.tm.exception.EntityNotExistsException;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.DTOUtil;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor
@WebService
@Component
@Scope(scopeName = "singleton")
public final class UserEndPointImplement implements UserEndPoint {
    @NotNull
    private ServiceLocator serviceLocator;

    public UserEndPointImplement(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public Session authorisation(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                                 @WebParam(name = "login") @Nullable final String login,
                                 @WebParam(name = "password") @Nullable final String password) throws AccessForbiddenException, EntityIsAlreadyExistException, EntityNotExistsException {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L) {
            throw new AccessForbiddenException();
        }
        if (login == null || login.isEmpty())
            throw new AccessForbiddenException();
        if (password == null || password.isEmpty())
            throw new AccessForbiddenException();

        UserService userService = serviceLocator.getUserService();
        User user = null;
        user = userService.find(login, ConsoleUtil.getHash(password));
        if (user == null)
        throw new EntityNotExistsException();
        return serviceLocator.getSessionService().openSession(login, ConsoleUtil.getHash(password));
    }

    @WebMethod
    public Session registration(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                                @WebParam(name = "login") @Nullable final String login,
                                @WebParam(name = "password") @Nullable final String password) throws AccessForbiddenException, EntityIsAlreadyExistException {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L)
            throw new AccessForbiddenException();
        if (login == null || login.isEmpty())
            throw new AccessForbiddenException();
        if (password == null || password.isEmpty())
            throw new AccessForbiddenException();
        UserService userService = serviceLocator.getUserService();
        if (userService.findByLogin(login) != null)
            throw new AccessForbiddenException();
        userService.persist(new User(login, ConsoleUtil.getHash(password), RoleType.USER));
        return serviceLocator.getSessionService().openSession(login, ConsoleUtil.getHash(password));
    }

    @WebMethod
    public Session updateLogin(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                               @WebParam(name = "newLogin") @Nullable final String newLogin
    ) throws AccessForbiddenException, EntityIsAlreadyExistException {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L)
            throw new AccessForbiddenException();
        if (session == null)
            throw new AccessForbiddenException();
        serviceLocator.getSessionService().validate(session);
        if (newLogin == null || newLogin.isEmpty())
            throw new AccessForbiddenException();
        if (serviceLocator.getUserService().findByLogin(newLogin) != null)
            throw new EntityIsAlreadyExistException();
        User user = serviceLocator.getUserService().findById(session.getId());
        user.setLogin(newLogin);
        serviceLocator.getUserService().merge(user);
        return session;
    }

    @WebMethod
    public Session updatePassword(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO,
                                  @WebParam(name = "newPassword") @Nullable final String newPassword) throws AccessForbiddenException {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L)
            throw new AccessForbiddenException();
        if (session == null)
            throw new AccessForbiddenException();
        if (newPassword == null || newPassword.isEmpty())
            throw new AccessForbiddenException();
        User user = null;
        user = serviceLocator.getUserService().findById(session.getId());
        user.setPassword(ConsoleUtil.getHash(newPassword));
        serviceLocator.getUserService().merge(user);
        return session;
    }

    @WebMethod
    public User getProfile(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws AccessForbiddenException {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L)
            throw new AccessForbiddenException();
        if (session == null)
            throw new AccessForbiddenException();
        serviceLocator.getSessionService().validate(session);
        return serviceLocator.getUserService().findById(session.getId());
    }

    @WebMethod
    public Session logout(@WebParam(name = "session") @Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);

        if (!session.getSignature().isEmpty() && !session.getUserId().isEmpty() && session.getTimeStamp() != 0L)
            throw new AccessForbiddenException();
        if (session == null)
            throw new AccessForbiddenException();
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().closeSession(session);
        return new Session();
    }
}
