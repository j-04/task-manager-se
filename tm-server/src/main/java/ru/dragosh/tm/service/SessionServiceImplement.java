package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.api.SessionService;
import ru.dragosh.tm.api.UserService;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.exception.EntityIsAlreadyExistException;
import ru.dragosh.tm.repository.SessionRepository;
import ru.dragosh.tm.util.SignatureUtil;

import javax.persistence.EntityManager;
import java.util.*;

@Component
@Scope(scopeName = "singleton")
public final class SessionServiceImplement implements SessionService {
    @NotNull
    private final String serviceId = UUID.randomUUID().toString();

    @Qualifier("entityManagerFactory")
    @NotNull
    @Autowired
    private EntityManager entityManager;

    @NotNull
    @Autowired
    private SessionRepository sessionRepository;

    @NotNull
    @Autowired
    @Qualifier("serviceLocatorBean")
    private ServiceLocator serviceLocator;

    @Override
    public void validate(@Nullable final Session session) throws AccessForbiddenException {
        if(session == null)
            throw new AccessForbiddenException();
        if(session.getSignature() == null || session.getSignature().isEmpty())
            throw new AccessForbiddenException();
        if(session.getUserId() == null || session.getUserId().isEmpty())
            throw new AccessForbiddenException();
        if(session.getTimeStamp() == null)
            throw new AccessForbiddenException();
        @NotNull final Session temp = session.clone();
        if(temp == null)
            throw new AccessForbiddenException();
        @NotNull final String signatureSource = session.getSignature();
        @Nullable final String signatureTarget = sign(temp).getSignature();
        final boolean check = signatureSource.equals(signatureTarget);
        if(!check)
            throw new AccessForbiddenException();
    }

    @Override
    public void validate(@Nullable final Session session,
                         @Nullable final RoleType roleType) throws AccessForbiddenException {
        if(roleType == null)
            throw new AccessForbiddenException();
        validate(session);
        if(session == null)
            throw new AccessForbiddenException();
        @Nullable final String userId = session.getUserId();
        @Nullable final User user = serviceLocator.getUserService().findById(userId);
        if(user == null)
            throw new AccessForbiddenException();
        if(user.getRole() == null)
            throw new AccessForbiddenException();
        if(!roleType.equals(user.getRole()))
            throw new AccessForbiddenException();
    }

    @Override
    public void validateRole(@Nullable Session session, @Nullable RoleType... roleTypes) throws AccessForbiddenException {
        if (session == null)
            throw new AccessForbiddenException();
        if (roleTypes == null)
            throw new AccessForbiddenException();
        @Nullable User user = serviceLocator.getUserService().findById(session.getId());
        if (user == null)
            throw new AccessForbiddenException();
        Set<RoleType> roleTypeSet = new HashSet<>(Arrays.asList(roleTypes));
        if (!roleTypeSet.contains(user.getRole()))
            throw new AccessForbiddenException();
    }

    @Nullable
    @Override
    public Session sign(@Nullable final Session session) {
        if (session == null) return null;
        session.setSignature(null);
        @Nullable final String signature = SignatureUtil.sign(session, "Task_Manager", 147);
        session.setSignature(signature);
        return session;
    }

    @NotNull
    @Override
    public List<Session> getSessionList() {
        entityManager.getTransaction().begin();
        List<Session> list = sessionRepository.findAll();
        entityManager.getTransaction().commit();
        return list;
    }

    @NotNull
    @Override
    public Session openSession(@Nullable final String login,
                               @Nullable final String password) throws AccessForbiddenException, EntityIsAlreadyExistException {
        if(serviceLocator == null)
            throw new AccessForbiddenException();
        @NotNull final UserService userService = serviceLocator.getUserService();
        @NotNull final User user = userService.find(login, password);
        @NotNull final Session session = new Session();
        session.setUserId(user.getId());
        session.setTimeStamp(1447L);
        @Nullable final Session signedSession = sign(session);
        if(signedSession == null)
            throw new AccessForbiddenException();
        entityManager.getTransaction().begin();
        sessionRepository.save(signedSession);
        entityManager.getTransaction().commit();
        return signedSession;
    }

    @Override
    public void closeSession(@Nullable final Session session) throws Exception {
        entityManager.getTransaction().begin();
        validate(session);
        sessionRepository.delete(session.getId());
        entityManager.getTransaction().commit();
    }

    @Override
    public String getServiceId() {
        return this.serviceId;
    }
}
