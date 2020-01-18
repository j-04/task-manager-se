package ru.dragosh.tm.api;

import ru.dragosh.tm.entity.Domain;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Serializer {
    void save(Domain domain) throws Exception;
    Domain Load() throws IOException, JAXBException;
}
