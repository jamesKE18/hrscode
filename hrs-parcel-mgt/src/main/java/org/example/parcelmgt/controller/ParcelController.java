package org.example.parcelmgt.controller;

import io.swagger.annotations.ApiOperation;
import org.example.parcelmgt.entity.GlobalRestResponse;
import org.example.parcelmgt.entity.Parcel;
import org.example.parcelmgt.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcel-sys")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    // todo: Input validation
    @PostMapping("/parcel")
    @ApiOperation(value = "Receive new parcel", notes = "Adds a parcel into the system, " +
            "if recipient is not in the hotel, reject the parcel")
    public GlobalRestResponse<Object> addParcel(@RequestBody Parcel parcel) {
        parcelService.addParcel(parcel);
        return GlobalRestResponse.ok();
    }

    @GetMapping("/parcel")
    @ApiOperation(value = "List parcels", notes = "List all uncollected parcels of a guest")
    public GlobalRestResponse<List<Parcel>> listParcel(@RequestParam String recipient) {
        return GlobalRestResponse.ok(parcelService.listParcel(recipient));
    }

    @PostMapping("/parcel/collection")
    @ApiOperation(value = "Collect parcels", notes = "List all uncollected parcels of a guest " +
            "and deliver them to guest")
    public GlobalRestResponse<List<Parcel>> collectParcel(@RequestParam String recipient) {
        return GlobalRestResponse.ok(parcelService.collectParcel(recipient));
    }
}
