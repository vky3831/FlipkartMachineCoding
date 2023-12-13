package org.example.service.impl;

import org.example.dao.DbOperations;
import org.example.enums.TaskStatus;
import org.example.models.Sprint;
import org.example.models.Story;
import org.example.models.SubTask;
import org.example.models.Task;
import org.example.service.DisplayService;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DisplayServiceImpl implements DisplayService {

    DbOperations dbOperations;

    public DisplayServiceImpl(DbOperations dbOperations){
        this.dbOperations = dbOperations;
    }

    @Override
    public void displayTasksByAssignee(String assignee) {
       List<Task> taskList = dbOperations.getTaskByAssignee(assignee);
        System.out.println("Task by Assignee: " + assignee);
       for(Task task : taskList){
           System.out.println("TaskType =>" + task.getTaskType());
           System.out.println("Title =>" + task.getTitle());
           if(Objects.nonNull(task.getSprint())){
                System.out.println("Sprint =>" + task.getSprint().getSprintId());
           }
           if(task instanceof Story){
               Story story = (Story) task;
               List<SubTask> subTaskList = story.getSubTasks();
               if(!subTaskList.isEmpty()){
                   System.out.println("Subtasks: ");
                   for(SubTask subTask: subTaskList){
                       System.out.println(subTask.getTitle());
                   }
               }

           }
           System.out.println("---");
       }
        System.out.println("-----------------------");
    }

    @Override
    public void displaySprintSnapshot(String sprintId) {
        Sprint sprint = dbOperations.getSprintById(sprintId);
        List<Task> taskList = sprint.getTaskList();
        System.out.println("Sprint snapshot for sprintId: " + sprintId);

        Date today = new Date();
        for(Task task : taskList){
            if(task.getDueDate().before(today) && !TaskStatus.isTerminalState(task.getStatus())){
                System.out.println("Delayed Task => " + task.getTitle());
            }else if(TaskStatus.isTerminalState(task.getStatus())){
                System.out.println("Completed Task => " + task.getTitle());
            }else{
                System.out.println("In progress Task => " + task.getTitle());
            }
        }

        System.out.println("-----------------------");
    }
}
