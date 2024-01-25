package org.example.guestmgt.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.guestmgt.entity.Guest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Manages all in-house guest info including their name (assuming it is an id in this task),
 * check-in time, estimated check-out time
 * <p>
 * Ideally it should be stored in a DB, but for simplicity purposes,
 * this class is created to simulate a DAO layer using a HashMap
 */
@Slf4j
@Component
public class GuestManager {
    private final Map<String, Guest> guests = new HashMap<>();

    public void checkIn(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest parameter is null");
        }
        guests.put(guest.getName(), guest);
    }

    public boolean checkOut(String name) {
        if (!guests.containsKey(name)) {
            log.warn("Guest [{}] is not in-house", name);
            return false;
        }
        guests.remove(name);
        return true;
    }

    public List<Guest> listInHouseGuests(String name) {
        if (name == null) {
            // query all in house guests
            return new ArrayList<>(guests.values());
        }
        if (!isGuestInHouse(name)) {
            return new ArrayList<>(0);
        }
        return Collections.singletonList(guests.get(name));
    }

    public boolean isGuestInHouse(String name) {
        return guests.containsKey(name) && guests.get(name).isCheckedIn();
    }
}
