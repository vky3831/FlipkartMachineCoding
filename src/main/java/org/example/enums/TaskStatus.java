package org.example.enums;

public enum TaskStatus {
    OPEN,
    IN_PROGRESS,
    TESTING,
    DEPLOYED,
    FIXED,
    COMPLETED;

    public static boolean isTerminalState(TaskStatus taskStatus){
        if(taskStatus == FIXED || taskStatus == DEPLOYED || taskStatus == COMPLETED){
            return true;
        }
        return false;
    }
}
