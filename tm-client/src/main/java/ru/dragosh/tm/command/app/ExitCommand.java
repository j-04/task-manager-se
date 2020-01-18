package ru.dragosh.tm.command.app;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.UserEndPoint;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

public final class ExitCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "exit";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(после завершения ввода названия/названий введите \"exit\" + Enter)";
    }

    @Override
    public void execute() {
        UserEndPoint userEndPoint = serviceLocator.getUserEndPointService().getUserEndPointPort();
        userEndPoint.logout(serviceLocator.getCurrentSession());
        ConsoleUtil.log(MessageType.PROGRAM_SHUTDOWN);
        System.exit(0);
    }
}
