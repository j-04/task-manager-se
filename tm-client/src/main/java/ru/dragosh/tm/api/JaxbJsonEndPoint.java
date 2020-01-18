package ru.dragosh.tm.api;

import ru.dragosh.tm.dto.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBException;
import java.io.IOException;


@WebService(name = "JaxbJsonEndPoint", targetNamespace = "http://endpoint.tm.dragosh.ru/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface JaxbJsonEndPoint {
    @WebMethod
    void save(SessionDTO session) throws IOException, JAXBException, Exception;
    @WebMethod
    void load(SessionDTO session) throws IOException, JAXBException;
}
