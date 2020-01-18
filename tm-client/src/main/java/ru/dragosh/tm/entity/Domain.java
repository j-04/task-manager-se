package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.dragosh.tm.dto.ProjectDTO;
import ru.dragosh.tm.dto.TaskDTO;
import ru.dragosh.tm.dto.UserDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "Domain")
public final class Domain implements Serializable {
    @Getter
    @Setter
    private List<ProjectDTO> projectList = new ArrayList<>();
    @Getter
    @Setter
    private List<TaskDTO> taskList = new ArrayList<>();
    @Getter
    @Setter
    private List<UserDTO> userList = new ArrayList<>();
}
