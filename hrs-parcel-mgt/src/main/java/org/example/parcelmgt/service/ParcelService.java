package org.example.parcelmgt.service;

import lombok.extern.slf4j.Slf4j;
import org.example.parcelmgt.dao.ParcelManager;
import org.example.parcelmgt.entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ParcelService {

    @Autowired
    private ParcelManager parcelManager;

    public void addParcel(Parcel parcel) {
        // todo: check if the recipient is in-house
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
