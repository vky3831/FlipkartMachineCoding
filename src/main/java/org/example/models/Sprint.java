package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Sprint {
    String sprintId;
    List<Task> taskList;

    public Sprint(){
        this.sprintId = UUID.randomUUID().toString();
        this.taskList = new ArrayList<>();
    }

    public boolean addTask(Task task){
        this.taskList.add(task);
        return true;
    }

    public boolean removeTask(Task task){
        for (Task task_iterator : taskList){
            if(task == task_iterator){
                taskList.remove(task);
                return true;
            }
        }
        System.out.println("Task not found in the sprint");
        return false;
    }
}
