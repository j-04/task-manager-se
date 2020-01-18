package ru.dragosh.tm.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "Exception", targetNamespace = "http://endpoint.tm.dragosh.ru/")
public class Exception_Exception extends Exception {
    private Exception fault;

    public Exception_Exception() {
        super("Доступ запрещен!");
    }

    public void setFault(final Exception fault) {
        this.fault = fault;
    }
}
