package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.DataBinEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class DataBinEndPointService extends Service {
    private final static URL DATABIN_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException  DATABIN_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName  DATABIN_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "DataBinEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/DataBinEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        DATABIN_ENDPOINT_IMPL_SERVICE_WSDL = url;
        DATABIN_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public DataBinEndPointService() {
        super( DATABIN_ENDPOINT_IMPL_SERVICE_WSDL,  DATABIN_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "DataBinEndPointImplementPort")
    public DataBinEndPoint getDataBinEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "DataBinEndPointImplementPort"), DataBinEndPoint.class);
    }
}
