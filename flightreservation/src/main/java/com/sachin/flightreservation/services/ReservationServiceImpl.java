package com.sachin.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sachin.flightreservation.dto.ReservationRequest;
import com.sachin.flightreservation.entities.Flight;
import com.sachin.flightreservation.entities.Passenger;
import com.sachin.flightreservation.entities.Reservation;
import com.sachin.flightreservation.repos.FlightRepository;
import com.sachin.flightreservation.repos.PassengerRepository;
import com.sachin.flightreservation.repos.ReservationRepository;
import com.sachin.flightreservation.util.EmailUtil;
import com.sachin.flightreservation.util.PDFGenerator;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${com.sachin.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;
	// "C:\\Users\\sachi\\OneDrive\\Desktop\\Flights_Reservation_Project\\reservations\\";

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PDFGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		// Make Payment

		LOGGER.info("Inside bookFlight()");

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching Flight for flightId " + flightId);
		Flight flight = flightRepository.findById(flightId).get();

		// Passenger Details
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());

		LOGGER.info("Saving the passenger: " + passenger);
		Passenger savePassenger = passengerRepository.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savePassenger);
		reservation.setCheckedIn(false);

		LOGGER.info("Saving the reservation: " + reservation);
		Reservation saveReservation = reservationRepository.save(reservation);

		String filePath = "C:\\Users\\sachi\\OneDrive\\Desktop\\Flights_Reservation_Project\\reservations\\reservation"
				+ saveReservation.getId() + ".pdf";

		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinerary(saveReservation, filePath);

		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(savePassenger.getEmail(), filePath);

		return saveReservation;
	}

}
