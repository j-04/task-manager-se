package ru.dragosh.tm.service.serializer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.Serializer;
import ru.dragosh.tm.entity.Domain;

import java.io.*;

@Component
@Scope(scopeName = "singleton")
public final class DataBinServiceImplement implements Serializer {
    @Override
    public void save(@NotNull final Domain domain) {
        File workingDirectory = new File("src" + File.separator + "resources");
        File file = new File(workingDirectory + "domain.bin");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(domain);
        } catch (IOException e) {
            System.out.println("MESSAGE -> DataBinService: Ошибка ввода вывода при сохранении!");
        }
    }

    @Nullable
    @Override
    public Domain Load() {
        File workingDirectory = new File("src" + File.separator + "resources");
        File file = new File(workingDirectory + "domain.bin");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Domain) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("MESSAGE -> DataBinService: Ошибка ввода вывода при загрузке!");
        }
        return null;
    }
}
