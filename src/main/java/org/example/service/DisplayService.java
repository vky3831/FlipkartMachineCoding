package org.example.service;

import org.example.models.Sprint;

public interface DisplayService {
    void displayTasksByAssignee(String assignee);

    void displaySprintSnapshot(String sprintId);
}
