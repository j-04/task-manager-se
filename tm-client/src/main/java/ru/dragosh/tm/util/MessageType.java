package ru.dragosh.tm.util;

public enum MessageType {
    PERMISSION_DENIED("Неавторизованным пользователям доступ запрещен!"),
    USER_NOT_FOUND("Пользователь с таким данными не найден!"),
    USER_EXISTS("Пользователь с таким логином уже существует!"),
    PROJECT_NOT_FOUND("Проект с таким названием не найден!"),
    PROJECT_EXISTS("Проект с таким названием уже существует!"),
    TASK_NOT_FOUND("Задача с таким названием не найдена!"),
    TASK_EXISTS("Задача с таким названием уже существует!"),
    WRONG_DATA_FORMAT("Введенные данные не соответствуют формату. Проверьте их корректность!"),
    WRONG_DATE_FORMAT("Введенные даты не соответствуют формату. Проверьте их на корректность!"),
    WRONG_COMMAND("Неверная комманда!"),
    PROGRAM_SHUTDOWN("Программа завершила свою работу!"),
    PROJECT_BORDER("**** PROJECT  ****"),
    PROJECTS_BORDER("**** PROJECTS ****"),
    TASK_BORDER("****   TASK   ****"),
    USER_BORDER("****   User   ****"),
    END_BORDER("******************"),
    WELCOME_MESSAGE("*** WELCOME TO TASK MANAGER ***"),
    HELP_MESSAGE("Для получения подробной справки по командам введите 'help'");

    private String message;

    MessageType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
