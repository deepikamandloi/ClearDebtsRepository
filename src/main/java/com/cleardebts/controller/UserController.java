package com.cleardebts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleardebts.exception.ClearDebtsException;
import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.UserInput;
import com.cleardebts.frontend.output.UserRegistrationOutput;
import com.cleardebts.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserRegistrationOutput> registerUser(@RequestBody UserInput userInput) throws Exception {

		UserRegistrationOutput registrationOutput;

		try {
			registrationOutput = userService.registerUser(userInput);

		} catch (ClearDebtsException cde) {
			registrationOutput = new UserRegistrationOutput();
			registrationOutput.setSuccess(false);
			registrationOutput.setMessage(cde.getMessage());
			registrationOutput.setErrorCode(cde.getErrorCode());
		}
		return ResponseEntity.ok(registrationOutput);
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<UserInput> getUser(@PathVariable Long id) throws RecordNotFoundException {
		
		return ResponseEntity.ok(userService.getUserById(id));
	}

}
