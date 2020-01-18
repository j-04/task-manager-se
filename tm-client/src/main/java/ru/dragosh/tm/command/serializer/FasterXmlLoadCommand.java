package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.FasterXmlEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.FasterXmlEndPointService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public final class FasterXmlLoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "faster load bases in xml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(загрузка баз данных в формате xml с помощью faster)";
    }

    @Override
    public void execute() {
        FasterXmlEndPointService fasterXmlEndPointService = new FasterXmlEndPointService();
        FasterXmlEndPoint fasterXmlEndPoint = fasterXmlEndPointService.getFasterXmlEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            fasterXmlEndPoint.load(session);
        } catch (IOException | JAXBException e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
