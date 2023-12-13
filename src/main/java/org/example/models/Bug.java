package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Severity;

@Data
@NoArgsConstructor
public class Bug extends Task{
    public Bug(String creator){
        this.creator = creator;
    }

    Severity severity;
}
