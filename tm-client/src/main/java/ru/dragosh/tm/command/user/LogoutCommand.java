package ru.dragosh.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.UserEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.UserEndPointService;

public final class LogoutCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "logout";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(выход из аккаунта)";
    }

    @Override
    public void execute() {
        UserEndPointService userEndPointService = serviceLocator.getUserEndPointService();
        UserEndPoint userEndPoint = userEndPointService.getUserEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();
        userEndPoint.logout(session);
    }
}
