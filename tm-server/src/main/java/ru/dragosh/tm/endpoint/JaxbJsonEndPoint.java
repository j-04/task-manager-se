package ru.dragosh.tm.endpoint;

import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public interface JaxbJsonEndPoint {
    @WebMethod
    void save(SessionDTO session) throws Exception;
    @WebMethod
    void load(SessionDTO session) throws Exception;
}
