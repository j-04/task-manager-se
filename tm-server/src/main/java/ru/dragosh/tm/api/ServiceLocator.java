package ru.dragosh.tm.api;

import ru.dragosh.tm.service.serializer.*;

import java.sql.Connection;

public interface ServiceLocator {
    SessionService getSessionService();
    ProjectService getProjectService();
    TaskService getTaskService();
    UserService getUserService();
    DataBinServiceImplement getDataBinServiceImplement();
    FasterJsonServiceImplement getFasterJsonServiceImplement();
    FasterXmlServiceImplement getFasterXmlServiceImplement();
    JaxbJsonServiceImplement getJaxbJsonServiceImplement();
    JaxbXmlServiceImplement getJaxbXmlServiceImplement();
}
