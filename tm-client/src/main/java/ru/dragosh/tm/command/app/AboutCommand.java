package ru.dragosh.tm.command.app;

import com.jcabi.manifests.Manifests;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.util.ConsoleUtil;
import ru.dragosh.tm.util.MessageType;

import java.util.HashSet;
import java.util.Set;

public final class AboutCommand extends AbstractCommand {
    @NotNull
    @Override
    public String getName() {
        return "about";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "(вывод информации о сборке)";
    }

    @Override
    public void execute() {
        try {
            System.out.println("JAR was created by " + Manifests.read("Developer")
                    + " " + Manifests.read("AppVersion"));
        } catch (IllegalArgumentException ex) {
            ConsoleUtil.log(MessageType.WRONG_DATA_FORMAT);
        }
    }
}
