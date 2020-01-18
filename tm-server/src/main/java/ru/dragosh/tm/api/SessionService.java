package ru.dragosh.tm.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dragosh.tm.entity.Session;
import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.exception.AccessForbiddenException;
import ru.dragosh.tm.exception.EntityIsAlreadyExistException;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface SessionService {
    void validate(@Nullable Session session) throws AccessForbiddenException;
    void validate(@Nullable Session session, @Nullable RoleType roleType) throws AccessForbiddenException;
    void validateRole(@Nullable Session session, @Nullable RoleType... roleTypes) throws AccessForbiddenException;
    @Nullable Session sign(@Nullable Session session);
    @NotNull
    List<Session> getSessionList();
    @NotNull Session openSession(@Nullable String login, @Nullable String password) throws AccessForbiddenException, EntityIsAlreadyExistException;
    void closeSession(@Nullable Session session) throws Exception;
    String getServiceId();
}
