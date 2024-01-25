package org.example.parcelmgt.service;

import org.example.parcelmgt.entity.GlobalRestResponse;
import org.example.parcelmgt.entity.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class GuestService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${third-party.guest.base-url}")
    private String guestServiceBaseUrl;

    public Guest queryInHouseGuest(String guestName) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> ticketRequestEntity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder
                .fromUriString(guestServiceBaseUrl + "/guest/in-house" +
                        "?name={name}")
                .build()
                .expand(guestName)
                .encode()
                .toUri();
        ResponseEntity<GlobalRestResponse<List<Guest>>> response =
                restTemplate.exchange(uri, HttpMethod.GET, ticketRequestEntity,
                        new ParameterizedTypeReference<GlobalRestResponse<List<Guest>>>() {
                        });
        // todo: response status check here
        if (response.getBody().getData().isEmpty()) {
            return null;
        }
        return response.getBody().getData().get(0);
    }
}
