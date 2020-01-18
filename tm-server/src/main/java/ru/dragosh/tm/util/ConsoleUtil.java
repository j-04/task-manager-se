package ru.dragosh.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public final class ConsoleUtil {
    public static void log(@NotNull final MessageType message) {
        System.out.println("MESSAGE -> " + message.getMessage());
    }

    public static void projectOutput(@NotNull final Project project) {
        System.out.println(MessageType.PROJECT_BORDER.getMessage());
        System.out.println(project);
        System.out.println(MessageType.END_BORDER.getMessage());
    }

    public static void taskOutput(@NotNull final Task task) {
        System.out.println(MessageType.TASK_BORDER.getMessage());
        System.out.println(task);
        System.out.println(MessageType.END_BORDER.getMessage());
    }

    public static void userOutput(@NotNull final User user) {
        System.out.println(MessageType.USER_BORDER.getMessage());
        System.out.println(user);
        System.out.println(MessageType.END_BORDER.getMessage());
    }

    @NotNull
    public static String readCommand() {
        System.out.print("Введите команду -> ");
        @NotNull Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    @NotNull
    public static String getHash(@NotNull final String line) {
        @Nullable MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка создания инстанса хеш-функции!");
            return "";
        }
        return Arrays.toString(md.digest(line.getBytes()));
    }
}
