package ru.dragosh.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dragosh.tm.entity.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {
    List<Session> findAll();

    Session findByUserId(String id);

    Session save(Session session);

    void delete(String id);

    void deleteAll();
}
