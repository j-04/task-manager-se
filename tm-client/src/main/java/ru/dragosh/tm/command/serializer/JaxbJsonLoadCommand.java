package ru.dragosh.tm.command.serializer;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.JaxbJsonEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.JaxbJsonEndPointService;

public final class JaxbJsonLoadCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "jaxb load bases in json";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(загрузка баз данных в формате json с помощью jaxb)";
    }

    @Override
    public void execute() {
        JaxbJsonEndPointService jaxbJsonEndPointService = new JaxbJsonEndPointService();
        JaxbJsonEndPoint jaxbJsonEndPoint = jaxbJsonEndPointService.getJaxbJsonEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        try {
            jaxbJsonEndPoint.load(session);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки данных!");
        }
    }
}
