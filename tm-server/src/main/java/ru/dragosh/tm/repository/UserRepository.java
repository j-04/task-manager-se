package ru.dragosh.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dragosh.tm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    User getById(String id);

    User save(User user);

    void deleteById(String id);

    List<User> getEntitiesList();
}
