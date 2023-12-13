package org.example.service.impl;

import org.example.dao.DbOperations;
import org.example.models.Sprint;
import org.example.models.Task;
import org.example.service.SprintService;

import java.util.Objects;

public class SprintServiceImpl implements SprintService {
    String defaultValue = "Default";

    DbOperations dbOperations;

    public SprintServiceImpl(DbOperations dbOperations){
        this.dbOperations = dbOperations;
    }


    @Override
    public Sprint createSprint() {
        Sprint sprint = new Sprint();
        dbOperations.saveSprint(sprint);
        return sprint;
    }

    @Override
    public boolean deleteSprint(Sprint sprint) {
        return dbOperations.deleteSprint(sprint);
    }

    @Override
    public boolean addTaskToSprint(Sprint sprint, Task task) {
        Sprint prevSprint = task.getSprint();
        if(Objects.nonNull(prevSprint)) {
            System.out.println("Removing task from previous sprint");
            boolean removed = removeTaskFromSprint(prevSprint, task);
            if(!removed){
                System.out.println("Unable to remove the task from previous sprint");
                return false;
            }
        }
        sprint.addTask(task);
        task.setSprint(sprint);
        return true;
    }

    @Override
    public boolean removeTaskFromSprint(Sprint sprint, Task task) {
        return sprint.removeTask(task);
    }
}
