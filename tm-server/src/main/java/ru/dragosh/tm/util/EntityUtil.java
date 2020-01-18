package ru.dragosh.tm.util;

import ru.dragosh.tm.enumeration.RoleType;
import ru.dragosh.tm.enumeration.Status;

import java.util.HashMap;

public final class EntityUtil {
    public static RoleType getRoleById(String id) {
        return RoleType.ADMIN.getId().equals(id)? RoleType.ADMIN : RoleType.USER;
    }

    public static Status getStatus(String id) {
        return new HashMap<String, Status>() {{
            put(Status.FINISHED.getId(), Status.FINISHED);
            put(Status.PROCESSING.getId(), Status.PROCESSING);
            put(Status.SCHEDULED.getId(), Status.SCHEDULED);
        }}.get(id);
    }
}
