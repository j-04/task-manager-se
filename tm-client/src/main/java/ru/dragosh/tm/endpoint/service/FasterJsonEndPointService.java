package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.DataBinEndPoint;
import ru.dragosh.tm.api.FasterJsonEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class FasterJsonEndPointService extends Service {
    private final static URL FASTER_JSON_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException FASTER_JSON_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName FASTER_JSON_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "FasterJsonEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/FasterJsonEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        FASTER_JSON_ENDPOINT_IMPL_SERVICE_WSDL = url;
        FASTER_JSON_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public FasterJsonEndPointService() {
        super(FASTER_JSON_ENDPOINT_IMPL_SERVICE_WSDL,  FASTER_JSON_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "FasterJsonEndPointImplementPort")
    public FasterJsonEndPoint getFasterJsonEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "FasterJsonEndPointImplementPort"), FasterJsonEndPoint.class);
    }
}
