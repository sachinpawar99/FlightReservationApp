<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Reservation Details</title>
</head>
<body>
<h2>Flight Details</h2>

Airlines: ${findReservation.flight.operatingAirlines}<br/>
Flight No: ${findReservation.flight.flightNumber}<br/>
Departure City: ${findReservation.flight.departureCity}<br/>
Arrival City: ${findReservation.flight.arrivalCity}<br/>
Date Of Departure: ${findReservation.flight.dateOfDeparture}<br/>
Estimate Departure Time: ${findReservation.flight.estimatedDepartureTime}<br/>

<h2>Passenger Details:</h2>

First Name: ${findReservation.passenger.firstName}<br/>
Last Name: ${findReservation.passenger.lastName}<br/>
Email : ${findReservation.passenger.email}<br/>
Phone : ${findReservation.passenger.phone}<br/>

<form action="completeCheckIn" method="post">
Enter the Number Of Bages you want to check in: <input type="text" name="numberOfBags" />
<input type="hidden" value="${findReservation.id}" name="reservationId" />
<input type="submit" value="Check In" />
</form>


</body>
</html>