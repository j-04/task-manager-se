package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.JaxbXmlEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.JaxbXmlEndPointService;

public final class JaxbXmlSaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "jaxb save bases in xml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(сохранение баз данных в формате xml с помощью jaxb)";
    }

    @Override
    public void execute() {
        JaxbXmlEndPointService jaxbXmlEndPointService = serviceLocator.getJaxbXmlEndPointService();
        JaxbXmlEndPoint jaxbXmlEndPoint = jaxbXmlEndPointService.getJaxbXmlEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            jaxbXmlEndPoint.save(session);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
