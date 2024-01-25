package org.example.guestmgt.controller;

import io.swagger.annotations.ApiOperation;
import org.example.guestmgt.entity.GlobalRestResponse;
import org.example.guestmgt.entity.Guest;
import org.example.guestmgt.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;
    
    // todo: input validation
    @PostMapping("/check-in")
    @ApiOperation(value = "Guest Check-in", notes = "Checks a guest into the hotel")
    public GlobalRestResponse<Object> checkIn(@RequestBody Guest guest) {
        guestService.checkIn(guest);
        return GlobalRestResponse.ok();
    }

    @PostMapping("/check-out")
    @ApiOperation(value = "Guest Check-out", notes = "Checks a guest out of the hotel")
    public GlobalRestResponse<Object> checkOut(@RequestBody Guest guest) {
        guestService.checkOut(guest.getName());
        return GlobalRestResponse.ok();
    }

    // Guest name as sensitive data in the query url of GET call is exposing user info.
    // But considering in practice we would normally use an anonymous id instead,
    // it is used in this task to follow REST api conventions
    @GetMapping("/in-house")
    @ApiOperation(value = "List in-house guests", notes = "List all the in-house guests, " +
            "if parameter is provided, check if that specific gueest is in the hotel")
    public GlobalRestResponse<List<Guest>> listInHouseGuests(
            @RequestParam(required = false) String name) {
        return GlobalRestResponse.ok(guestService.listGuests(name));
    }
}
