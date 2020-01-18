package ru.dragosh.tm.api;

import ru.dragosh.tm.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface Repository<E extends Entity> {
    void persist(E entity) throws ParseException, SQLException;
    void merge(E entity) throws SQLException;
    void remove(String userId, String entityId) throws SQLException;

    List<E> getEntitiesList() throws SQLException;

    List<E> getSortedBySystemTime(String userId) throws SQLException;
    List<E> getSortedByDateStart(String userId) throws SQLException;
    List<E> getSortedByDateFinish(String userId) throws SQLException;
    List<E> getSortedByStatus(String userId) throws SQLException;
}
