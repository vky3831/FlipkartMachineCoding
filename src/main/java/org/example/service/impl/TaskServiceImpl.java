package org.example.service.impl;

import org.example.dao.DbOperations;
import org.example.enums.*;
import org.example.models.*;
import org.example.service.TaskService;

import java.util.*;

public class TaskServiceImpl implements TaskService {
    String defaultValue = "Default";

    DbOperations dbOperations;

    public TaskServiceImpl(DbOperations dbOperations){
        this.dbOperations = dbOperations;
    }

    @Override
    public Task createTask(TaskType taskType, String creator, String title, Date dueDate, Map<String, Object> typeSpecificDetails) {
        switch (taskType) {
            case FEATURE:
                return createAndSaveFeature(taskType, creator, title, dueDate, typeSpecificDetails);
            case BUG:
                return createAndSaveBug(taskType, creator, title, dueDate, typeSpecificDetails);
            case STORY:
                return createAndSaveStory(taskType, creator, title, dueDate, typeSpecificDetails);
        }

        return null;
    }

    private Task createAndSaveFeature(TaskType taskType, String creator, String title, Date dueDate, Map<String, Object> typeSpecificDetails) {
        String featureSummary = typeSpecificDetails.getOrDefault("featureSummary", defaultValue).toString();
        String assignee = typeSpecificDetails.getOrDefault("assignee", defaultValue).toString();
        Impact impact = (Impact) typeSpecificDetails.getOrDefault("impact", Impact.LOW);

        Feature feature = new Feature(creator);
        feature.setFeatureSummary(featureSummary);
        feature.setImpact(impact);
        feature.setAssignee(assignee);
        feature.setTitle(title);
        feature.setDueDate(dueDate);
        feature.setTaskType(taskType);
        feature.setStatus(TaskStatus.OPEN);

        dbOperations.saveTask(feature);

        return feature;
    }

    private Task createAndSaveBug(TaskType taskType, String creator, String title, Date dueDate, Map<String, Object> typeSpecificDetails) {
        String assignee = typeSpecificDetails.getOrDefault("assignee", defaultValue).toString();
        Severity severity = (Severity) typeSpecificDetails.getOrDefault("severity", Severity.P2);

        Bug bug = new Bug(creator);
        bug.setSeverity(severity);
        bug.setAssignee(assignee);
        bug.setTitle(title);
        bug.setDueDate(dueDate);
        bug.setTaskType(taskType);
        bug.setStatus(TaskStatus.OPEN);

        dbOperations.saveTask(bug);

        return bug;
    }

    private Task createAndSaveStory(TaskType taskType, String creator, String title, Date dueDate, Map<String, Object> typeSpecificDetails) {
        String summary = typeSpecificDetails.getOrDefault("summary", defaultValue).toString();
        String assignee = typeSpecificDetails.getOrDefault("assignee", defaultValue).toString();

        Story story = new Story(creator);
        story.setAssignee(assignee);
        story.setTitle(title);
        story.setDueDate(dueDate);
        story.setTaskType(taskType);
        story.setStatus(TaskStatus.OPEN);
        story.setSummary(summary);

        dbOperations.saveTask(story);

        return story;
    }

    @Override
    public SubTask createSubTask(Story story, String title) {
        if(story.getStatus() == TaskStatus.COMPLETED){
            throw new RuntimeException("Story is already completed");
        }
        SubTask subTask = new SubTask(story);
        subTask.setTitle(title);
        subTask.setSubTaskStatus(SubTaskStatus.OPEN);
        story.addSubTask(subTask);
        return subTask;
    }

    @Override
    public boolean changeTaskStatus(Task task, TaskStatus taskStatus) {
        if(!StoryConditionEligibility(task, taskStatus)){
            System.out.println("Invalid Operation! You are trying to close a story without completing all subtasks");
        }

        try {
            task.changeStatus(taskStatus);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    private boolean StoryConditionEligibility(Task task, TaskStatus taskStatus) {
        if(task instanceof Story && taskStatus == TaskStatus.COMPLETED){
            Story story = (Story) task;
            List<SubTask> subTaskList = story.getSubTasks();
            for(SubTask subTask : subTaskList){
                if(subTask.getSubTaskStatus() != SubTaskStatus.COMPLETED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean changeSubTaskStatus(SubTask subTask, SubTaskStatus subTaskStatus) {
        subTask.changeStatus(subTaskStatus);
        return true;
    }

    @Override
    public boolean changeAssigneeOfTask(Task task, String assignee) {
        task.changeAssignee(assignee);
        return true;
    }
}
