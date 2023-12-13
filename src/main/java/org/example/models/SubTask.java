package org.example.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.example.enums.SubTaskStatus;

@Data
public class SubTask {

    @Setter(AccessLevel.NONE)
    Story story;

    String title;
    SubTaskStatus subTaskStatus;

    public SubTask(Story story){
        this.story = story;
    }

    public boolean changeStatus(SubTaskStatus subTaskStatus){
        this.setSubTaskStatus(subTaskStatus);
        return true;
    }
}
