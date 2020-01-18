package ru.dragosh.tm.endpoint;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public interface JaxbXmlEndPoint {
    @WebMethod
    void save(SessionDTO sessionDTO) throws Exception;
    @WebMethod
    void load(SessionDTO sessionDTO) throws Exception;
}
