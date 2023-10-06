package com.sachin.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sachin.flightreservation.entities.Flight;
import com.sachin.flightreservation.entities.Passenger;
import com.sachin.flightreservation.entities.Reservation;

@Component
public class PDFGenerator {

//	public void generateItinerary(Reservation reservation, String filePath) {
//
//		Document document = new Document();
//
//		try {
//			PdfWriter.getInstance(document, new FileOutputStream(filePath));
//
//			System.out.println(reservation.getId()+" "+reservation.getNumberOfBags());
//			
//			document.open();
//			document.add(generateTable(reservation));
//			document.close();
//
//		}
//
//		catch (FileNotFoundException | DocumentException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private PdfPTable generateTable(Reservation reservation) {
//		PdfPTable table = new PdfPTable(2);
//
//		PdfPCell cell;
//		cell = new PdfPCell(new Phrase("Flight Itinerary"));
//		cell.setColspan(2);
//		table.addCell(cell);
//
//		table.addCell("Airlines");
//		table.addCell(reservation.getFlight().getOperatingAirlines());
//
//		table.addCell("Departure City");
//		table.addCell(reservation.getFlight().getDepartureCity());
//
//		table.addCell("Arrival City");
//		table.addCell(reservation.getFlight().getArrivalCity());
//
//		table.addCell("Flight Number");
//		table.addCell(reservation.getFlight().getFlightNumber());
//
//		table.addCell("Departure Date");
//		table.addCell(reservation.getFlight().getDateOfDeparture().toString());
//
//		table.addCell("Departure Time");
//		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
//
//		cell = new PdfPCell(new Phrase("Passenger Details"));
//		cell.setColspan(2);
//		table.addCell(cell);
//
//		table.addCell("First Name");
//		table.addCell(reservation.getPassenger().getFirstName());
//
//		table.addCell("Last Name");
//		table.addCell(reservation.getPassenger().getLastName());
//
//		table.addCell("Email");
//		table.addCell(reservation.getPassenger().getEmail());
//
//		table.addCell("Phone");
//		table.addCell(reservation.getPassenger().getPhone());
//
//		return null;
//	}

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

	public void generateItinerary(Reservation reservation, String filePath) {

		LOGGER.info("Inside generateItinerary()");

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			document.add(generateTable(reservation)); // Add the table to the document
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			LOGGER.error("Exception in generateItinerary " + e);
		}
	}

	private PdfPTable generateTable(Reservation reservation) {

		LOGGER.info("Inside generateTable()");

		PdfPTable table = new PdfPTable(2);

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);

		Flight flight = reservation.getFlight();
		if (flight != null) {
			table.addCell("Airlines");
			table.addCell(flight.getOperatingAirlines());

			table.addCell("Departure City");
			table.addCell(flight.getDepartureCity());

			table.addCell("Arrival City");
			table.addCell(flight.getArrivalCity());

			table.addCell("Flight Number");
			table.addCell(flight.getFlightNumber());

			table.addCell("Departure Date");
			table.addCell(flight.getDateOfDeparture().toString());

			table.addCell("Departure Time");
			table.addCell(flight.getEstimatedDepartureTime().toString());
		} else {
			// Handle the case where
			LOGGER.info("flight is null");
			// You can add error handling or log a message here
		}

		Passenger passenger = reservation.getPassenger();
		if (passenger != null) {
			cell = new PdfPCell(new Phrase("Passenger Details"));
			cell.setColspan(2);
			table.addCell(cell);

			table.addCell("First Name");
			table.addCell(passenger.getFirstName());

			table.addCell("Last Name");
			table.addCell(passenger.getLastName());

			table.addCell("Email");
			table.addCell(passenger.getEmail());

			table.addCell("Phone");
			table.addCell(passenger.getPhone());
		} else {
			// Handle the case where 'passenger' is null
			// You can add error handling or log a message here
			LOGGER.info("passenger is null");
		}

		return table; // Return the PdfPTable
	}
}
