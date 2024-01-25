package org.example.guestmgt.entity;

import lombok.Data;

import java.util.List;

@Data
public class GuestCheckOutResponse {
    private boolean checkedOut;
    private List<Parcel> uncollectedParcels;
}
