package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Impact;
import org.example.enums.TaskStatus;
import org.example.enums.TaskType;

import java.util.Date;

@Data
public class Feature extends Task{

    public Feature(String creator){
        this.creator = creator;
    }

    String featureSummary;
    Impact impact;

}
