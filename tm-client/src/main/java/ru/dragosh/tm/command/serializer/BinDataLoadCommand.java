package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.DataBinEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.DataBinEndPointService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public final class BinDataLoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "load bases in bytes";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(загрузка баз данных из файла с последовательностью байтов)";
    }

    @Override
    public void execute() {
        DataBinEndPointService dataBinEndPointService = serviceLocator.getDataBinEndPointService();
        DataBinEndPoint dataBinEndPoint = dataBinEndPointService.getDataBinEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            dataBinEndPoint.load(session);
        } catch (IOException | JAXBException e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
