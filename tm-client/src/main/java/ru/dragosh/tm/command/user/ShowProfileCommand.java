package ru.dragosh.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.UserEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.UserEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;

public final class ShowProfileCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "show profile";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод информации о профиле пользователя)";
    }

    @Override
    public void execute() {
        UserEndPointService userEndPointService = serviceLocator.getUserEndPointService();
        UserEndPoint userEndPoint = userEndPointService.getUserEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        ConsoleUtil.userOutput(userEndPoint.getProfile(session));
    }
}
