package ru.dragosh.tm.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.entity.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findAll(String userId, String projectId);

    Task findByUserAndProjectAndName(User user, Project project, String nameTask);

    void deleteAllByUserAndProject(User user, Project project);

    @Query("select t from task t where t.user = :user and t.project = :project and (t.task_name LIKE %:str% OR t.description LIKE %:str%)")
    List<Task> findByStringPart(@Param(value = "user") User user,
                                @Param(value = "project") Project project,
                                @Param(value = "str") String str);

    Project save(Project project);

    void deleteByUserAndId(User user, String id);

    List<Task> findAll();

    List<Task> findByUser(User user);
}