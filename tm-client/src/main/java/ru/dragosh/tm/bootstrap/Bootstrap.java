package ru.dragosh.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dragosh.tm.api.ServiceLocator;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.*;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class Bootstrap implements ServiceLocator {
    @NotNull
    @Getter
    @Autowired
    private Map<String, AbstractCommand> commands;
    @NotNull
    @Getter
    @Autowired
    private ProjectEndPointService projectEndPointService;
    @NotNull
    @Getter
    @Autowired
    private TaskEndPointService taskEndPointService;
    @NotNull
    @Getter
    @Autowired
    private UserEndPointService userEndPointService;
    @NotNull
    @Getter
    @Autowired
    private DataBinEndPointService dataBinEndPointService;
    @NotNull
    @Getter
    @Autowired
    private FasterJsonEndPointService fasterJsonEndPointService;
    @NotNull
    @Getter
    @Autowired
    private FasterXmlEndPointService fasterXmlEndPointService;
    @NotNull
    @Getter
    @Autowired
    private JaxbJsonEndPointService jaxbJsonEndPointService;
    @NotNull
    @Getter
    @Autowired
    private JaxbXmlEndPointService jaxbXmlEndPointService;
    @Setter
    @Autowired
    private SessionDTO currentSession;
    @NotNull
    @Autowired
    private List<Class<? extends AbstractCommand>> classes;

    public void init() {
        prepareCommands();
        start();
    }

    private void start() {
        ConsoleUtil.log(MessageType.WELCOME_MESSAGE);
        ConsoleUtil.log(MessageType.HELP_MESSAGE);
        while (true) {
            @NotNull String stringCommand = ConsoleUtil.readCommand().toLowerCase();
            @Nullable AbstractCommand command = commands.get(stringCommand);

            if (command == null) {
                ConsoleUtil.log(MessageType.WRONG_COMMAND);
                continue;
            }

            try {
                command.execute();
            } catch (Exception e) {
                System.out.println("MESSAGE -> Ошибка выполнения комманды!");
            }
        }
    }

    private void prepareCommands() {
        @NotNull final Predicate<Class<? extends AbstractCommand>> commandClassPredicate = (commandClass) -> {
            try {
                return (Class.forName(commandClass.getCanonicalName()).newInstance()) instanceof AbstractCommand;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                System.out.println("MESSAGE -> Ошибка создания инстанса команды!");
            }
            return false;
        };
        classes.stream()
                .filter(commandClassPredicate)
                .forEach(commandClass -> {
                    try {
                        AbstractCommand abstractCommand = commandClass.newInstance();
                        commands.put(abstractCommand.getName(), abstractCommand);
                    } catch (InstantiationException | IllegalAccessException e) {
                        System.out.println("MESSAGE -> Ошибка создания инстанса команды!");
                    }
                });
    }

    @Override
    public SessionDTO getCurrentSession() {
        return this.currentSession;
    }

    @Override
    public void setCurrentSession(SessionDTO session) {
        this.currentSession = session;
    }
}
