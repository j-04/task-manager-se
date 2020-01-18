package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.JaxbJsonEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class JaxbJsonEndPointService extends Service {
    private final static URL JAXB_JSON_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException JAXB_JSON_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName JAXB_JSON_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "JaxbJsonEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/JaxbJsonEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        JAXB_JSON_ENDPOINT_IMPL_SERVICE_WSDL = url;
        JAXB_JSON_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public JaxbJsonEndPointService() {
        super(JAXB_JSON_ENDPOINT_IMPL_SERVICE_WSDL,  JAXB_JSON_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "JaxbJsonEndPointImplementPort")
    public JaxbJsonEndPoint getJaxbJsonEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "JaxbJsonEndPointImplementPort"), JaxbJsonEndPoint.class);
    }
}
