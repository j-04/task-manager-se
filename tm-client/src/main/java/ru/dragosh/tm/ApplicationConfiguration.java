package ru.dragosh.tm;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.dragosh.tm.command.AbstractCommand;
import ru.dragosh.tm.dto.SessionDTO;
import ru.dragosh.tm.endpoint.service.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
public class ApplicationConfiguration {
    @Bean
    public ProjectEndPointService getProjectEndPointService() {
        return new ProjectEndPointService();
    }

    @Bean
    public TaskEndPointService getTaskEndPointService() {
        return new TaskEndPointService();
    }

    @Bean
    public UserEndPointService getUserEndPointService() {
        return new UserEndPointService();
    }

    @Bean
    public DataBinEndPointService getDataBinEndPointService() {
        return new DataBinEndPointService();
    }

    @Bean
    public FasterJsonEndPointService getFasterJsonEndPointService() {
        return new FasterJsonEndPointService();
    }

    @Bean
    public FasterXmlEndPointService getFasterXmlEndPointService() {
        return new FasterXmlEndPointService();
    }

    @Bean
    public JaxbJsonEndPointService getJaxbJsonEndPointService() {
        return new JaxbJsonEndPointService();
    }

    @Bean
    public JaxbXmlEndPointService getJaxbXmlEndPointService() {
        return new JaxbXmlEndPointService();
    }

    @Bean
    public Map<String, AbstractCommand> getEmptyMapCommands() {
        return new LinkedHashMap<>();
    }

    @Bean
    public List<Class<? extends AbstractCommand>> getCommands() {
        return new ArrayList<>(new Reflections("ru.dragosh.tm").getSubTypesOf(AbstractCommand.class));
    }

    @Bean
    public SessionDTO getSessionDTO() {
        return new SessionDTO();
    }
}
