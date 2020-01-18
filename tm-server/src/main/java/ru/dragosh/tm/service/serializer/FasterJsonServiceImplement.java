package ru.dragosh.tm.service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
public final class FasterJsonServiceImplement implements Serializer {
    @Override
    public void save(@NotNull final Domain domain) throws IOException {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        @NotNull final String json = objectWriter.writeValueAsString(domain);
        @NotNull final byte[] data = json.getBytes(StandardCharsets.UTF_8);
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        if (!workingFolder.exists()) workingFolder.mkdir();
        @NotNull final File domainFile = new File(workingFolder, "domain.json");
        Files.write(domainFile.toPath(), data);
    }

    @Nullable
    @Override
    public Domain Load() throws IOException {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final File workingFolder = new File("src" + File.separator + "resources");
        @NotNull final File domainFile = new File(workingFolder, "domain.json");
        if (!domainFile.exists()) return null;
        @NotNull final Domain domain = objectMapper.readValue(domainFile, Domain.class);
        return domain;
    }
}
