package ru.dragosh.tm.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "EntityIsAlreadyExistException", targetNamespace = "http://endpoint.tm.dragosh.ru/")
public final class EntityIsAlreadyExistException extends Exception {
}
