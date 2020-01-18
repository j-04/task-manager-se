package ru.dragosh.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.User;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAllByUser(User user);

    Project findByNameAndUser(String projectName, User user);

    void removeAllByUser(User user);

    @Query("select p from project where user = :user and (p.project_name LIKE %:str% OR p.description LIKE %:str%)")
    List<Project> findByStringPart(User user, String str);

    void deleteByUserAndId(User user, String entityId);

    List<Project> findByUser(User user);

    void deleteByUser(User user);
}