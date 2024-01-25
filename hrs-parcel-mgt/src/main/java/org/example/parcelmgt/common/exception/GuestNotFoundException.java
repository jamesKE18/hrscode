package org.example.parcelmgt.common.exception;

import lombok.Getter;

/**
 * An exception that indicates guest is not found in the hotel
 */
@Getter
public class GuestNotFoundException extends RuntimeException {
    private final String guestName;

    public GuestNotFoundException(String guestName, String message) {
        super(message);
        this.guestName = guestName;
    }
}
