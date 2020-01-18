package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.DataBinEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.DataBinEndPointService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public final class BinDataSaveCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "save bases in bytes";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(сохранение баз данных в последовательность байтов)";
    }

    @Override
    public void execute() {
        DataBinEndPointService dataBinEndPointService = serviceLocator.getDataBinEndPointService();
        DataBinEndPoint dataBinEndPoint = dataBinEndPointService.getDataBinEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            dataBinEndPoint.save(session);
        } catch (IOException | JAXBException e) {
            System.out.println("Ошибка загрузки данных!");
        } catch (Exception e) {
            System.out.println("Ошибка загрзуки данных!");
        }
    }
}
