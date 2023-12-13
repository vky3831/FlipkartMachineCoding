package org.example.service;

import org.example.enums.SubTaskStatus;
import org.example.enums.TaskStatus;
import org.example.enums.TaskType;
import org.example.models.Story;
import org.example.models.SubTask;
import org.example.models.Task;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public interface TaskService {
    Task createTask(TaskType taskType, String creator, String title, Date dueDate, Map<String, Object> typeSpecificDetails);

    SubTask createSubTask(Story story, String title);

    boolean changeTaskStatus(Task task, TaskStatus taskStatus);

    boolean changeSubTaskStatus(SubTask subTask, SubTaskStatus subTaskStatus);

    boolean changeAssigneeOfTask(Task task, String assignee);
}
