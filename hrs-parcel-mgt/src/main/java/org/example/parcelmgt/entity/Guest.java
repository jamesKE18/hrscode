package org.example.parcelmgt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class Guest {
    private String name;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTimeEst;

    @JsonIgnore
    public boolean isCheckedIn() {
        return checkInTime != null && LocalDateTime.now().isAfter(checkInTime);
    }
}
