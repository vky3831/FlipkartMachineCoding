package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Story extends Task{

    String summary;
    List<SubTask> subTasks;

    public Story(String creator){
        this.creator = creator;
        this.subTasks = new ArrayList<>();
    }

    public boolean addSubTask(SubTask subTask){
        subTasks.add(subTask);
        return true;
    }
}
