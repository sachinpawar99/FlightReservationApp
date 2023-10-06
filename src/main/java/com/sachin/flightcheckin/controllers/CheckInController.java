package com.sachin.flightcheckin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sachin.flightcheckin.integration.ReservationRestClient;
import com.sachin.flightcheckin.integration.dto.Reservation;
import com.sachin.flightcheckin.integration.dto.ReservationUpdateRequest;

@Controller
//@CrossOrigin
public class CheckInController {

	@Autowired
	private ReservationRestClient restClient;

	private static final Logger LOGGER = LoggerFactory.getLogger(CheckInController.class);

	@RequestMapping("/showStartCheckin")
	public String showStartCheckin() {

		return "startCheckIn";
	}

	@RequestMapping("/startCheckIn")
	public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap map) {
		Reservation findReservation = restClient.findReservation(reservationId);

		map.addAttribute("findReservation", findReservation);

		return "displayReservationDetails";
	}

	// @RequestMapping("/completeCheckIn")
	//@Transactional
	@RequestMapping(value = "/completeCheckIn", method = RequestMethod.POST)
	public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
			@RequestParam("numberOfBags") int numberOfBags) {
		LOGGER.info("Inside completeCheckIn() ");
		ReservationUpdateRequest updateRequest = new ReservationUpdateRequest();
		updateRequest.setId(reservationId);
		updateRequest.setCheckedIn(true);
		updateRequest.setNumberOfBags(numberOfBags);

		LOGGER.info("Request has been update " + updateRequest);
		Reservation updateReservation = restClient.updateReservation(updateRequest);
		
		LOGGER.info("Confirm updateReservation "+updateReservation);

		return "checkInConfirmation";
	}

}