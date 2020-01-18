package ru.dragosh.tm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "Domain")
@NoArgsConstructor
public final class Domain implements Serializable {
    @Getter
    @Setter
    private List<Project> projectList = new ArrayList<>();
    @Getter
    @Setter
    private List<Task> taskList = new ArrayList<>();
    @Getter
    @Setter
    private List<User> userList = new ArrayList<>();
}
