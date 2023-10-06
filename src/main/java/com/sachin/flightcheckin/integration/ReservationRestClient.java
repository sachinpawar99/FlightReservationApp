package com.sachin.flightcheckin.integration;

import com.sachin.flightcheckin.integration.dto.Reservation;
import com.sachin.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {
	
	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);

}
