package org.example.models;

import lombok.*;
import org.example.enums.TaskStatus;
import org.example.enums.TaskType;


import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public abstract class Task {
    String title;
    @Setter(AccessLevel.NONE)
    String creator;
    String assignee;
    TaskStatus status;
    TaskType taskType;
    Date dueDate;
    Sprint sprint;

    public void changeStatus(TaskStatus newStatus) {
        Set<TaskStatus> allowedTransitions = getAllowedTransitions();
        if (allowedTransitions.contains(newStatus)) {
            this.status = newStatus;
        } else {
            throw new RuntimeException("Invalid status transition for task type " + this.taskType);
        }
    }

    private Set<TaskStatus> getAllowedTransitions() {
        Set<TaskStatus> allowedTransitions = new HashSet<>();
        switch (this.taskType) {
            case FEATURE:
                allowedTransitions.addAll(Arrays.asList(TaskStatus.OPEN, TaskStatus.IN_PROGRESS, TaskStatus.TESTING, TaskStatus.DEPLOYED));
                break;
            case BUG:
                allowedTransitions.addAll(Arrays.asList(TaskStatus.OPEN, TaskStatus.IN_PROGRESS, TaskStatus.FIXED));
                break;
            case STORY:
                allowedTransitions.addAll(Arrays.asList(TaskStatus.OPEN, TaskStatus.IN_PROGRESS, TaskStatus.COMPLETED));
                break;
        }
        return allowedTransitions;
    }

    public boolean changeAssignee(String assignee) {
        this.setAssignee(assignee);
        return true;
    }
}
