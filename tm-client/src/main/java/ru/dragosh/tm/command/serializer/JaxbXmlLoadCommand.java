package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.JaxbXmlEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.JaxbXmlEndPointService;

public final class JaxbXmlLoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "jaxb load bases in xml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(загрузка баз данных в формате xml с помошью jaxb)";
    }

    @Override
    public void execute() {
        JaxbXmlEndPointService jaxbXmlEndPointService = serviceLocator.getJaxbXmlEndPointService();
        JaxbXmlEndPoint jaxbXmlEndPoint = jaxbXmlEndPointService.getJaxbXmlEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            jaxbXmlEndPoint.load(session);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
