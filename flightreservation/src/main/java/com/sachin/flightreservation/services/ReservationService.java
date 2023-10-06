package com.sachin.flightreservation.services;

import com.sachin.flightreservation.dto.ReservationRequest;
import com.sachin.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
