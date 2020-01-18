package ru.dragosh.tm.dto.containers;

import lombok.Getter;
import lombok.Setter;
import ru.dragosh.tm.dto.ProjectDTO;

import java.util.List;

public final class ProjectList {
    @Getter
    @Setter
    private List<ProjectDTO> projectList;
}
