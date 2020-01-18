package ru.dragosh.tm.service.serializer;

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

@Component
@Scope(scopeName = "singleton")
public final class JaxbXmlServiceImplement implements Serializer {
    @Override
    public void save(final @NotNull Domain domain) throws JAXBException {
        @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
        @NotNull final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        if (!workingFolder.exists()) workingFolder.mkdir();
        @NotNull final File domainFile = new File(workingFolder, "domain.xml");
        marshaller.marshal(domain, domainFile);
    }

    @Nullable
    @Override
    public Domain Load() throws JAXBException {
        @NotNull final JAXBContext context = JAXBContext.newInstance(Domain.class);
        @NotNull final Unmarshaller unmarshaller = context.createUnmarshaller();
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        @NotNull final File domainFile = new File(workingFolder, "domain.xml");
        return (Domain) unmarshaller.unmarshal(domainFile);
    }
}
