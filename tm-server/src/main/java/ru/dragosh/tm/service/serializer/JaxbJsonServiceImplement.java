package ru.dragosh.tm.service.serializer;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.Serializer;
import ru.dragosh.tm.entity.Domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(scopeName = "singleton")
public final class JaxbJsonServiceImplement implements Serializer {
    @Override
    public void save(@NotNull final Domain domain) throws JAXBException {
        @NotNull final Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        @NotNull final JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[] {Domain.class}, properties);
        @NotNull final Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        if (!workingFolder.exists()) workingFolder.mkdir();
        @NotNull final File domainFile = new File(workingFolder, "domain.json");
        jaxbMarshaller.marshal(domain, domainFile);
    }

    @Nullable
    @Override
    public Domain Load() throws JAXBException {
        @NotNull final JAXBContext jaxbContext = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        jaxbUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
        jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        @NotNull final File domainFile = new File(workingFolder, "domain.json");
        return (Domain) jaxbUnmarshaller.unmarshal(domainFile);
    }
}
