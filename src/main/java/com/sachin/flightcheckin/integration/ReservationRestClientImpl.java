package com.sachin.flightcheckin.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sachin.flightcheckin.integration.dto.Reservation;
import com.sachin.flightcheckin.integration.dto.ReservationUpdateRequest;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

	@Value("${com.sachin.flightcheckin.RESERVATIONS.REST.URL}")
	private String RESERVATIONS_REST_URL;
	// = "http://localhost:8080/flightreservation/reservations/";

	@Override
	public Reservation findReservation(Long id) {

		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.getForObject(RESERVATIONS_REST_URL + id, Reservation.class);

		return reservation;
	}

	@Override
	public Reservation updateReservation(ReservationUpdateRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Reservation reservation = restTemplate.postForObject(RESERVATIONS_REST_URL, request, Reservation.class);

		return reservation;
	}

}