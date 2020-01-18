package ru.dragosh.tm.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "AccessForbiddenException", targetNamespace = "http://endpoint.tm.dragosh.ru/")
public final class AccessForbiddenException extends Exception {
    public AccessForbiddenException() {
        super("Доступ запрещен!");
    }
}
