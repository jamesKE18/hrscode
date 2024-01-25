package org.example.guestmgt.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class Guest {
    @NotBlank(message = "Guest name can't be blank")
    private String name;
    @NotNull(message = "Guest check in time can't be null")
    private LocalDateTime checkInTime;
    @NotNull(message = "Guest estimated check out time can't be null")
    private LocalDateTime checkOutTimeEst;

    public boolean isCheckedIn() {
        return checkInTime != null && LocalDateTime.now().isAfter(checkInTime);
    }
}
