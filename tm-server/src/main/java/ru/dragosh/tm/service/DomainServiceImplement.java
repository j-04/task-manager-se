package ru.dragosh.tm.service;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.dragosh.tm.api.DomainService;
import ru.dragosh.tm.entity.Domain;

public final class DomainServiceImplement implements DomainService {
    @NotNull
    @Getter
    private final Domain domain = new Domain();

    public void loadDomain(@NotNull final Domain domain) {
        if (domain == null)
            return;

        this.domain.setProjectList(domain.getProjectList());
        this.domain.setTaskList(domain.getTaskList());
        this.domain.setUserList(domain.getUserList());
    }
}
