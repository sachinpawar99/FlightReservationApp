//package com.sachin.flightreservation.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig {
//	
//	
//
//	@Bean
//	BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@SuppressWarnings("removal")
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//
//		httpSecurity.authorizeHttpRequests().requestMatchers("admin/showAddFlight").hasRole("ADMIN")
//				.requestMatchers("/showCompleteReservation*", "/findFlights", "/completeReservation",
//						"reservationConfirmation")
//				.authenticated().requestMatchers("/showReg", "/login/registerUser", "/registerUser", "/login/login",
//						"/login", "/", "index.html","/reservations/*")
//				.permitAll().and().csrf().disable();
//
//		return httpSecurity.build();
//	}
//	
//	
//	@Bean
//	   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//	       return authenticationConfiguration.getAuthenticationManager();
//	   }
//
//}
