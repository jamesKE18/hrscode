package org.example.guestmgt.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.guestmgt.dao.GuestManager;
import org.example.guestmgt.entity.Guest;
import org.example.guestmgt.entity.GuestCheckOutResponse;
import org.example.guestmgt.entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Setter
@Service
public class GuestService {
    @Autowired
    private GuestManager guestManager;

    @Autowired
    private ParcelService parcelService;

    public void checkIn(Guest guest) {
        guestManager.checkIn(guest);
        log.info("Guest [{}] is checked in", guest.getName());
    }

    public GuestCheckOutResponse checkOut(String name) {
        // Before guest checkout, check if there are any uncollected parcels
        List<Parcel> uncollectedParcels = parcelService.listParcel(name);
        boolean checkedOut = guestManager.checkOut(name);

        GuestCheckOutResponse response = new GuestCheckOutResponse();
        response.setUncollectedParcels(uncollectedParcels);
        response.setCheckedOut(checkedOut);
        if (checkedOut) {
            log.info("Guest [{}] is checked out, [Parcels]: {}", name, uncollectedParcels);
        }
        return response;
    }

    public List<Guest> listGuests(String name) {
        List<Guest> guests = guestManager.listInHouseGuests(name);
        log.info("Guests: [{}]", guests);
        return guests;
    }
}
