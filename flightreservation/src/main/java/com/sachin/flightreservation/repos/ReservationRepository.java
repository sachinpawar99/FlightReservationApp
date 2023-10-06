package com.sachin.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
