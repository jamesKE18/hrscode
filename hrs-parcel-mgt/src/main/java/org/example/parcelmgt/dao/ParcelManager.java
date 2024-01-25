package org.example.parcelmgt.dao;

import org.example.parcelmgt.entity.Parcel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ParcelManager {
    private final Map<String, List<Parcel>> parcelsMap = new HashMap<>();

    /**
     * Add a parcel to the system
     *
     * @param parcel the parcel to be added
     */
    public void addParcel(Parcel parcel) {
        if (parcel == null) {
            throw new IllegalArgumentException("Parcel can't be null");
        }
        List<Parcel> parcels = parcelsMap.getOrDefault(parcel.getRecipient(), new ArrayList<>());
        if (parcels.isEmpty()) {
            parcelsMap.put(parcel.getRecipient(), parcels);
        }
        parcels.add(parcel);
    }

    /**
     * List parcel in the system that belongs to a specific recipient
     *
     * @param recipient the parcel's owner
     * @return list of parcels, returns empty list if no parcel is waiting to be collected
     */
    public List<Parcel> listParcel(String recipient) {
        List<Parcel> parcels = parcelsMap.get(recipient);
        if (parcels == null) {
            return new ArrayList<>(0);
        }
        return parcels;
    }

    /**
     * Deliver parcel to the recipient and removes the parcel info in the system
     *
     * @param recipient the parcel's owner
     * @return list of parcels to be collected, returns empty list if no parcel is collected
     */
    public List<Parcel> collectParcel(String recipient) {
        List<Parcel> parcels = listParcel(recipient);
        parcelsMap.remove(recipient);
        return parcels;
    }
}
