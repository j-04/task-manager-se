package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.FasterXmlEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.FasterXmlEndPointService;

public final class FasterXmlSaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "faster save bases in xml";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(сохранение баз данных в формате xml с помошью faster)";
    }

    @Override
    public void execute() {
        FasterXmlEndPointService fasterXmlEndPointService = new FasterXmlEndPointService();
        FasterXmlEndPoint fasterXmlEndPoint = fasterXmlEndPointService.getFasterXmlEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            fasterXmlEndPoint.load(session);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
