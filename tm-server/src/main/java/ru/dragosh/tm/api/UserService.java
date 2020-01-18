package ru.dragosh.tm.api;

import ru.dragosh.tm.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User find(String login, String password);
    User findByLogin(String login);
    User findById(String id);
    void persist(User user);
    void merge(User user);
    void remove(String userId);

    List<User> getEntitiesList();
    void loadEntities(List<User> entities);
}
