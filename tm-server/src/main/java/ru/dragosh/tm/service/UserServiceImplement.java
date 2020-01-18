package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.api.UserService;
import ru.dragosh.tm.entity.User;
import ru.dragosh.tm.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Scope(scopeName = "singleton")
public final class UserServiceImplement implements UserService {
    @Qualifier("entityManagerFactory")
    @NotNull
    @Autowired
    private EntityManager entityManager;

    @NotNull
    @Autowired
    private UserRepository userRepository;

    @Nullable
    @Override
    public User find(@NotNull final String login,
                     @NotNull final String password) {
        if (login == null || login.isEmpty())
            return null;
        if (password == null || password.isEmpty())
            return null;
        entityManager.getTransaction().begin();
        User user = userRepository.findByLoginAndPassword(login, password);
        entityManager.getTransaction().commit();
        return user;
    }

    @Nullable
    @Override
    public User findByLogin(@NotNull final String login) {
        if (login == null || login.isEmpty())
            return null;
        entityManager.getTransaction().begin();
        User user = userRepository.findByLogin(login);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User findById(@NotNull final String id) {
        entityManager.getTransaction().begin();
        User user = userRepository.getById(id);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public void persist(@NotNull final User user) {
        if (user == null)
            return;
        entityManager.getTransaction().begin();
        userRepository.save(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void merge(@NotNull final User user) {
        if (user == null)
            return;
        entityManager.getTransaction().begin();
        userRepository.save(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(@NotNull final String userId) {
        if (userId == null || userId.isEmpty())
            return;
        entityManager.getTransaction().begin();
        userRepository.deleteById(userId);
        entityManager.getTransaction().commit();
    }

    @NotNull
    @Override
    public List<User> getEntitiesList() {
        entityManager.getTransaction().begin();
        List<User> list = userRepository.getEntitiesList();
        entityManager.getTransaction().commit();
        return list;
    }

    @Override
    public void loadEntities(@NotNull final List<User> entities) {
        if (entities == null)
            return;
        entityManager.getTransaction().begin();
        for (User user: entities) {
            userRepository.save(user);
        }
        entityManager.getTransaction().commit();
    }
}
