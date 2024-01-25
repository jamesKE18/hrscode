package org.example.guestmgt.service;

import lombok.extern.slf4j.Slf4j;
import org.example.guestmgt.dao.GuestManager;
import org.example.guestmgt.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GuestService {
    @Autowired
    private GuestManager guestManager;

    public void checkIn(Guest guest) {
        guestManager.checkIn(guest);
        log.info("Guest [{}] is checked in", guest.getName());
    }

    public void checkOut(String name) {
        boolean checkedOut = guestManager.checkOut(name);
        if (checkedOut) {
            log.info("Guest [{}] is checked out", name);
        }
    }

    public List<Guest> listGuests(String name) {
        List<Guest> guests = guestManager.listInHouseGuests(name);
        log.info("Guests: [{}]", guests);
        return guests;
    }
}
