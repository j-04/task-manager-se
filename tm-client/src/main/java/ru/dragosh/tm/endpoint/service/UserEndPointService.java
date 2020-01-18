package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.TaskEndPoint;
import ru.dragosh.tm.api.UserEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class UserEndPointService extends Service {
    private final static URL USER_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException USER_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName USER_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "UserEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/UserEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        USER_ENDPOINT_IMPL_SERVICE_WSDL = url;
        USER_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public UserEndPointService() {
        super(USER_ENDPOINT_IMPL_SERVICE_WSDL, USER_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "UserEndPointImplementPort")
    public UserEndPoint getUserEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "UserEndPointImplementPort"), UserEndPoint.class);
    }
}
