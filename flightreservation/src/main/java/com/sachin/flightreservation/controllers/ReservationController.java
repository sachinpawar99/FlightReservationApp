package com.sachin.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sachin.flightreservation.dto.ReservationRequest;
import com.sachin.flightreservation.entities.Flight;
import com.sachin.flightreservation.entities.Reservation;
import com.sachin.flightreservation.repos.FlightRepository;
import com.sachin.flightreservation.services.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationService reservationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap map) {

		LOGGER.info("Inside showCompleteReservation() " + flightId);

		Flight flight = flightRepository.findById(flightId).get();
		map.addAttribute("flight", flight);

		LOGGER.info("Flight is: " + flight);

		return "completeReservation";
	}

	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap map) {

		LOGGER.info("Inside completeReservation() " + request);

		Reservation reservation = reservationService.bookFlight(request);

		map.addAttribute("msg", "Reservation created Successfully and the id is " + reservation.getId());
		return "reservationConfirmation";
	}

}
