package com.sachin.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sachin.flightreservation.entities.User;
import com.sachin.flightreservation.repos.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage");
		return "login/registerUser";
	}

	@RequestMapping(value = "registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()" + user);
		userRepository.save(user);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password, ModelMap map) {

		User user = userRepository.findByEmail(email);		
		LOGGER.info("Inside login() and email is " + email);
		if(user!=null && user.getPassword().equals(password))
		{
			return "findFlights";
		}
		else
		{
			map.addAttribute("msg","Invalid user name or password. Please try again...!");
		}


		return "login/login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm() {

		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}

}
