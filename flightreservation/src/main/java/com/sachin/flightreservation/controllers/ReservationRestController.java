package com.sachin.flightreservation.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sachin.flightreservation.dto.ReservationUpdateRequest;
import com.sachin.flightreservation.entities.Reservation;
import com.sachin.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationRestController {

	@Autowired
	private ReservationRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: " + id);

		Optional<Reservation> optionalReservation = repository.findById(id);

		if (optionalReservation.isPresent()) {
			Reservation reservation = optionalReservation.get();
			return reservation;
		} else {
			LOGGER.info("reservation is found");
			return null; // Handle the case when no reservation is found
		}
	}

	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {

		LOGGER.info("Inside updateReservation() for " + request);
		Reservation reservation = repository.findById(request.getId()).orElse(null);
		if (reservation != null) {
			reservation.setNumberOfBags(request.getNumberOfBags());
			reservation.setCheckedIn(request.getCheckedIn());

			LOGGER.info("Saving Reservation");
			repository.save(reservation);
		}
		return reservation;
	}

}
