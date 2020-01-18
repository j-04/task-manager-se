package ru.dragosh.tm.entity;

import ru.dragosh.tm.enumeration.Status;

public interface Entity {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getDateStart();
    void setDateStart(String dateStart);
    String getDateFinish();
    void setDateFinish(String dateFinish);
    User getUser();
    void setUser(User user);
    Long getSystemTime();
    void setSystemTime(Long systemTime);
    Status getStatus();
    void setStatus(Status status);
}
