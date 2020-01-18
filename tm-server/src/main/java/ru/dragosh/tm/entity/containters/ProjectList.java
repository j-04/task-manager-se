package ru.dragosh.tm.entity.containters;

import lombok.Getter;
import lombok.Setter;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.entity.Project;

import java.util.List;

public final class ProjectList {
    @Getter
    @Setter
    private List<ProjectDTO> projectList;
}
