package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.FasterXmlEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class FasterXmlEndPointService extends Service {
    private final static URL FASTER_XML_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException FASTER_XML_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName FASTER_XML_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "FasterXmlEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/FasterXmlEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        FASTER_XML_ENDPOINT_IMPL_SERVICE_WSDL = url;
        FASTER_XML_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public FasterXmlEndPointService() {
        super(FASTER_XML_ENDPOINT_IMPL_SERVICE_WSDL,  FASTER_XML_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "FasterXmlEndPointImplementPort")
    public FasterXmlEndPoint getFasterXmlEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "FasterXmlEndPointImplementPort"), FasterXmlEndPoint.class);
    }
}
