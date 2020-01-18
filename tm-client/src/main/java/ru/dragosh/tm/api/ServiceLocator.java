package ru.dragosh.tm.api;

import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.*;

import java.util.Map;

public interface ServiceLocator {
    DataBinEndPointService getDataBinEndPointService();
    FasterJsonEndPointService getFasterJsonEndPointService();
    FasterXmlEndPointService getFasterXmlEndPointService();
    JaxbJsonEndPointService getJaxbJsonEndPointService();
    JaxbXmlEndPointService getJaxbXmlEndPointService();
    ProjectEndPointService getProjectEndPointService();
    TaskEndPointService getTaskEndPointService();
    UserEndPointService getUserEndPointService();

    Map<String, AbstractCommand> getCommands();
    SessionDTO getCurrentSession();
    void setCurrentSession(SessionDTO session);
}
