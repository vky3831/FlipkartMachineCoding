package org.example.service;

import org.example.models.Sprint;
import org.example.models.Task;

public interface SprintService {
    Sprint createSprint();
    boolean deleteSprint(Sprint sprint);

    boolean addTaskToSprint(Sprint sprint, Task task);

    boolean removeTaskFromSprint(Sprint sprint, Task task);
}
