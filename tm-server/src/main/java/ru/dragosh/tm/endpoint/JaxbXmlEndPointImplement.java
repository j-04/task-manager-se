package ru.dragosh.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Domain;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.util.DTOUtil;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Service;
import java.io.IOException;
import java.sql.Connection;

@NoArgsConstructor
@WebService
@Component
@Scope(scopeName = "singleton")
public final class JaxbXmlEndPointImplement implements JaxbXmlEndPoint {
    @NotNull
    private ServiceLocator serviceLocator;

    public JaxbXmlEndPointImplement(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void save(@Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validate(session);
        @NotNull Domain domain = new Domain();
        domain.setUserList(serviceLocator.getUserService().getEntitiesList());
        domain.setProjectList(serviceLocator.getProjectService().getEntitiesList());
        domain.setTaskList(serviceLocator.getTaskService().getEntitiesList());
        serviceLocator.getJaxbXmlServiceImplement().save(domain);
    }

    public void load(@Nullable final SessionDTO sessionDTO) throws Exception {
        if (sessionDTO == null)
            throw new AccessForbiddenException();
        final Session session = DTOUtil.sessionDTOToSession(sessionDTO);
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getSessionService().validate(session, RoleType.ADMIN);
        @Nullable final Domain domain = serviceLocator.getJaxbXmlServiceImplement().Load();
        if (domain == null)
            throw new AccessForbiddenException();
        serviceLocator.getTaskService().loadEntities(domain.getTaskList());
        serviceLocator.getUserService().loadEntities(domain.getUserList());
        serviceLocator.getProjectService().loadEntities(domain.getProjectList());
        serviceLocator.getSessionService().closeSession(session);
    }
}
