package ru.dragosh.tm.command.app;

import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.enumeration.RoleType;

import java.util.HashSet;
import java.util.Set;

public final class HelpCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "help";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Подробная справка по командам.";
    }

    @Override
    public void execute() {
        serviceLocator.getCommands().forEach((name, command) -> {
            if (!name.equals(this.getName()) && command != this)
                System.out.println("COMMAND -> " + name + " - " + command.getDescription());
        });
    }
}
