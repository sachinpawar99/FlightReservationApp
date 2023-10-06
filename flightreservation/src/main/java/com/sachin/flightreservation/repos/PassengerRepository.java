package com.sachin.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
