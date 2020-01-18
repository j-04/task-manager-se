package ru.dragosh.tm.command.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.api.UserEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.UserEndPointService;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class RegistrationCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "register";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(регистрация нового пользователя)";
    }

    @Override
    public void execute() {
        UserEndPointService userEndPointService = serviceLocator.getUserEndPointService();
        UserEndPoint userEndPoint = userEndPointService.getUserEndPointPort();
        SessionDTO session = serviceLocator.getCurrentSession();

        @Nullable String login = readWord("Введите ваш логин: ");
        @Nullable String password = readWord("Введите ваш пароль: ");
        if (login == null || login.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }

        if (password == null || password.isEmpty()) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
            return;
        }

        serviceLocator.setCurrentSession(userEndPoint.registration(session, login, password));
    }
}
