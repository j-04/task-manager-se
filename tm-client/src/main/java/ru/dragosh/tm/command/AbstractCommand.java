package ru.dragosh.tm.command;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.ServiceLocator;

import java.util.Scanner;

public abstract class AbstractCommand {
    @Getter
    @Setter
    protected ServiceLocator serviceLocator;

    public AbstractCommand() {
    }

    public abstract String getName();
    public abstract String getDescription();
    public abstract void execute() throws Exception;

    @NotNull
    public String readWord(@NotNull final String message) {
        System.out.print(message);
        @NotNull Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
