package org.example.dao.impl;

import org.example.dao.DbOperations;
import org.example.models.Sprint;
import org.example.models.SubTask;
import org.example.models.Task;

import java.util.ArrayList;
import java.util.List;

public class DbOperationsImpl implements DbOperations {
    List<Task> taskList = new ArrayList<>();
    List<Sprint> sprintList = new ArrayList<>();


    @Override
    public boolean saveTask(Task task) {
        taskList.add(task);
        return true;
    }

    @Override
    public boolean saveSprint(Sprint sprint) {
        sprintList.add(sprint);
        return true;
    }

    @Override
    public boolean deleteSprint(Sprint sprint) {
        for (Sprint sprint_iterator : sprintList){
            if(sprint == sprint_iterator){
                sprintList.remove(sprint);
                return true;
            }
        }
        System.out.println("Sprint not found");
        return false;
    }

    @Override
    public List<Task> getTaskByAssignee(String assignee) {
        List<Task> taskByAssignee = new ArrayList<>();
        for (Task task : taskList){
            if(task.getAssignee().equalsIgnoreCase(assignee)){
                taskByAssignee.add(task);
            }
        }
        return taskByAssignee;
    }

    @Override
    public Sprint getSprintById(String uuid) {
        for (Sprint sprint_iterator : sprintList){
            if(uuid.equalsIgnoreCase(sprint_iterator.getSprintId())){
                return sprint_iterator;
            }
        }
        return null;
    }
}
