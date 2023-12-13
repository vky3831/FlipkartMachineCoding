package org.example.dao;

import org.example.models.Sprint;
import org.example.models.SubTask;
import org.example.models.Task;

import java.util.List;

public interface DbOperations {

    boolean saveTask(Task task);
    boolean saveSprint(Sprint sprint);

    boolean deleteSprint(Sprint sprint);

    List<Task> getTaskByAssignee(String assignee);

    Sprint getSprintById(String uuid);
}
