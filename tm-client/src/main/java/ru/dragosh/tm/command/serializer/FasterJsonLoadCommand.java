package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.FasterJsonEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.FasterJsonEndPointService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public final class FasterJsonLoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "faster load bases in json";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(загрузка баз данных в формате json с помощью faster)";
    }

    @Override
    public void execute() {
        FasterJsonEndPointService fasterJsonEndPointService = new FasterJsonEndPointService();
        FasterJsonEndPoint fasterJsonEndPoint = fasterJsonEndPointService.getFasterJsonEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            fasterJsonEndPoint.load(session);
        } catch (IOException | JAXBException e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
