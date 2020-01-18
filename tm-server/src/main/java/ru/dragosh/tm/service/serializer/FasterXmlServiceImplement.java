package ru.dragosh.tm.service.serializer;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.Serializer;
import ru.dragosh.tm.entity.Domain;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Component
@Scope(scopeName = "singleton")
public final class FasterXmlServiceImplement implements Serializer {
    @Override
    public void save(@NotNull final Domain domain) throws IOException {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
        @NotNull final ObjectWriter objectWriter = xmlMapper.writerWithDefaultPrettyPrinter();
        @NotNull final String xml = objectWriter.writeValueAsString(domain);
        @NotNull final byte[] data = xml.getBytes(StandardCharsets.UTF_8);
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        if (!workingFolder.exists()) workingFolder.mkdir();
        @NotNull final File domainFile = new File(workingFolder, "domain.xml");
        Files.write(domainFile.toPath(), data);
    }

    @Nullable
    @Override
    public Domain Load() throws IOException {
        @NotNull final XmlMapper xmlMapper = new XmlMapper();
        @NotNull final File workingFolder = new File("src"+ File.separator+"resources");
        @NotNull final File domainFile = new File(workingFolder, "domain.xml");
        @NotNull final Domain domain = xmlMapper.readValue(domainFile, Domain.class);
        return domain;
    }
}
