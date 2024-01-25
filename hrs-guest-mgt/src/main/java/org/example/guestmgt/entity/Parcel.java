package org.example.guestmgt.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Parcel {
    private String recipient;
    private String content;
    private LocalDateTime timeReceived;
}
