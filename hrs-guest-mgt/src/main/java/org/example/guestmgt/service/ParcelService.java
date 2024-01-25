package org.example.guestmgt.service;

import org.example.guestmgt.entity.GlobalRestResponse;
import org.example.guestmgt.entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParcelService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${third-party.parcel.base-url}")
    private String parcelServiceBaseUrl;

    /**
     * list parcels received when guest checks out
     * Ideally in the microservice architecture, would use a service discovery center like eureka
     *
     * @param recipient the guest who's checking out
     * @return uncollected parcels by this guest
     */
    public List<Parcel> listParcel(String recipient) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> ticketRequestEntity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder
                .fromUriString(parcelServiceBaseUrl + "/parcel-sys/parcel" +
                        "?recipient={recipient}")
                .build()
                .expand(recipient)
                .encode()
                .toUri();
        ResponseEntity<GlobalRestResponse<List<Parcel>>> response =
                restTemplate.exchange(uri, HttpMethod.GET, ticketRequestEntity,
                        new ParameterizedTypeReference<GlobalRestResponse<List<Parcel>>>() {
                        });
        // todo: response status check here
        return response.getBody().getData();
    }
}
