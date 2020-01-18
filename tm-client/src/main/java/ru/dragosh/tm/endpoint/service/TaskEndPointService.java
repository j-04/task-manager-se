package ru.dragosh.tm.endpoint.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.TaskEndPoint;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@Scope(scopeName = "singleton")
public class TaskEndPointService extends Service {
    private final static URL TASK_ENDPOINT_IMPL_SERVICE_WSDL;
    private final static WebServiceException TASK_ENDPOINT_IMPL_SERVICE_EXCEPTION;
    private final static QName TASK_ENDPOINT_IMPL_SERVICE_QNAME = new QName("http://endpoint.tm.dragosh.ru/", "TaskEndPointImplementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ProjectEndPoint?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException();
        }
        TASK_ENDPOINT_IMPL_SERVICE_WSDL = url;
        TASK_ENDPOINT_IMPL_SERVICE_EXCEPTION = e;
    }

    public TaskEndPointService() {
        super(TASK_ENDPOINT_IMPL_SERVICE_WSDL, TASK_ENDPOINT_IMPL_SERVICE_QNAME);
    }

    @WebEndpoint(name = "TaskEndPointImplementPort")
    public TaskEndPoint getTaskEndPointPort() {
        return super.getPort(new QName("http://endpoint.tm.dragosh.ru/", "TaskEndPointImplementPort"), TaskEndPoint.class);
    }
}
