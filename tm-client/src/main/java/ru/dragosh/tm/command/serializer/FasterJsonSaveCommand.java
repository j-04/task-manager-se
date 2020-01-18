package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.FasterJsonEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.FasterJsonEndPointService;

public final class FasterJsonSaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "faster save bases in json";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(сохранение баз данных в формате json с помощью faster)";
    }

    @Override
    public void execute() {
        FasterJsonEndPointService fasterJsonEndPointService = new FasterJsonEndPointService();
        FasterJsonEndPoint fasterJsonEndPoint = fasterJsonEndPointService.getFasterJsonEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            fasterJsonEndPoint.save(session);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
