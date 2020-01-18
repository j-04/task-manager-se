package ru.dragosh.tm.bootstrap;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.*;
import ru.dragosh.tm.endpoint.*;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.repository.ProjectRepository;
import ru.dragosh.tm.repository.UserRepository;
import ru.dragosh.tm.service.ProjectServiceImplement;
import ru.dragosh.tm.service.SessionServiceImplement;
import ru.dragosh.tm.service.TaskServiceImplement;
import ru.dragosh.tm.service.UserServiceImplement;
import ru.dragosh.tm.service.serializer.*;
import ru.dragosh.tm.util.ConnectionUtil;
import ru.dragosh.tm.util.HibernateUtil;

import javax.xml.ws.Endpoint;
import java.sql.Connection;
import java.sql.SQLException;

@Component
@Scope(scopeName = "singleton")
public final class Bootstrap implements ServiceLocator {
    @NotNull
    @Getter
    @Autowired
    private ProjectService projectService;

    @NotNull
    @Getter
    @Autowired
    private UserService userService;

    @NotNull
    @Getter
    @Autowired
    private TaskService taskService;

    @NotNull
    @Getter
    @Autowired
    private SessionService sessionService;

    @NotNull
    @Getter
    @Autowired
    private DataBinServiceImplement dataBinServiceImplement;

    @NotNull
    @Getter
    @Autowired
    private FasterJsonServiceImplement fasterJsonServiceImplement;

    @NotNull
    @Getter
    @Autowired
    private FasterXmlServiceImplement fasterXmlServiceImplement;

    @NotNull
    @Getter
    @Autowired
    private JaxbJsonServiceImplement jaxbJsonServiceImplement;

    @NotNull
    @Getter
    @Autowired
    private JaxbXmlServiceImplement jaxbXmlServiceImplement;

    public void init() {
        publishEndPoints();
        prepareUsers();
    }

    private void publishEndPoints() {
        Endpoint.publish("http://localhost:8080/ProjectEndPoint", new ProjectEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/UserEndPoint", new UserEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/FasterJsonEndPoint", new FasterJsonEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/FasterXmlEndPoint", new FasterXmlEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/JaxbJsonEndPoint", new JaxbJsonEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/JaxbXmlEndPoint", new JaxbXmlEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/DataBinEndPoint", new DataBinEndPointImplement(this));
        Endpoint.publish("http://localhost:8080/TaskEndPoint", new TaskEndPointImplement(this));

        System.out.println("http://localhost:8080/ProjectEndPoint?wsdl");
        System.out.println("http://localhost:8080/TaskEndPoint?wsdl");
        System.out.println("http://localhost:8080/UserEndPoint?wsdl");
        System.out.println("http://localhost:8080/FasterJsonEndPoint?wsdl");
        System.out.println("http://localhost:8080/FasterXmlEndPoint?wsdl");
        System.out.println("http://localhost:8080/JaxbJsonEndPoint?wsdl");
        System.out.println("http://localhost:8080/JaxbXmlEndPoint?wsdl");
    }

    private void prepareUsers() {
        User user1 = new User("user", "user", RoleType.USER);
        User user2 = new User("root", "root", RoleType.ADMIN);
        userService.persist(user1);
        userService.persist(user2);
    }
}
