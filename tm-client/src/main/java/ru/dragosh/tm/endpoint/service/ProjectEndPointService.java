package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.ProjectEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class ProjectEndPointService extends Service {
    private final static URL PROJECT_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException PROJECT_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName PROJECT_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "ProjectEndPointImplementService");

    static {
            URL url = null;
            WebServiceException e = null;
            try {
                url = new URL("http://localhost:8080/ProjectEndPoint?wsdl");
            } catch (MalformedURLException ex) {
                e = new WebServiceException();
            }
            PROJECT_ENDPOINT_IMPL_SERVICE_WSDL = url;
            PROJECT_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public ProjectEndPointService() {
        super(PROJECT_ENDPOINT_IMPL_SERVICE_WSDL, PROJECT_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "ProjectEndPointImplementPort")
    public ProjectEndPoint getProjectEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "ProjectEndPointImplementPort"), ProjectEndPoint.class);
    }
}
