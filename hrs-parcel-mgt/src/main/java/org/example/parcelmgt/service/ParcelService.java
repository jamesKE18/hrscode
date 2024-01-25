package org.example.parcelmgt.service;

import lombok.extern.slf4j.Slf4j;
import org.example.parcelmgt.common.exception.GuestNotFoundException;
import org.example.parcelmgt.dao.ParcelManager;
import org.example.parcelmgt.entity.Guest;
import org.example.parcelmgt.entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParcelService {

    @Autowired
    private ParcelManager parcelManager;

    @Autowired
    private GuestService guestService;

    /**
     * Receives parcel if the recipient is still in-house, rejects otherwise
     *
     * @param parcel the parcel delivered to the hotel
     */
    public void addParcel(Parcel parcel) {
        // First check if the recipient is in-house
        Guest guest = guestService.queryInHouseGuest(parcel.getRecipient());
        if (guest == null) {
            log.warn("Guest [{}] is not in-house, parcel rejected", parcel.getRecipient());
            throw new GuestNotFoundException(parcel.getRecipient(),
                    "Guest is not in-house, parcel rejected");
        }
        // Guest is in-house
        parcelManager.addParcel(parcel);
        log.info("New parcel received. [Recipient]: {}, [Content]: {}",
                parcel.getRecipient(), parcel.getContent());
    }

    public List<Parcel> listParcel(String recipient) {
        List<Parcel> parcels = parcelManager.listParcel(recipient);
        log.info("Parcels for [{}]: [{}]", recipient, parcels);
        return parcels;
    }

    public List<Parcel> collectParcel(String recipient) {
        List<Parcel> parcels = parcelManager.collectParcel(recipient);
        log.info("Parcels collected by [{}]: [{}]", recipient, parcels);
        return parcels;
    }
}
