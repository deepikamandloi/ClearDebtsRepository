package com.cleardebts.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.UserInput;
import com.cleardebts.frontend.output.UserRegistrationOutput;
//import com.cleardebts.model.User;
//import com.cleardebts.repository.UserRepository;
import com.cleardebts.util.ErrorCodes;

@Service
public class UserService {

//	@Autowired
//	private UserRepository userRepository;

	// TEMP Section
	private static Map<Long, UserInput> userDetailsMap = new HashMap();

	private static Long id = 1L;

	public UserRegistrationOutput registerUser(UserInput userInput) {

		UserRegistrationOutput registrationOutput = new UserRegistrationOutput();

		try {
			if (userInput.getFirstName().equalsIgnoreCase("abcd")) {
				throw new Exception();
			}
			userDetailsMap.put(id, userInput);

			registrationOutput.setId(id);
			registrationOutput.setSuccessCode(ErrorCodes.SUCCESS_CODE);
			registrationOutput.setSuccessMessage("User saved successfully");
			id++;
		} catch (Exception e) {
			registrationOutput.setErrorCode(ErrorCodes.ERROR_CODE);
			registrationOutput.setErrorMessage("Problem occured during save operation");
		}

		return registrationOutput;
	}

	public UserInput getUserById(Long id) throws RecordNotFoundException {

		if (userDetailsMap.containsKey(id)) {
			return userDetailsMap.get(id);
		} else {
			throw new RecordNotFoundException("User details not found");
		}

	}

	// END TEMP Section

//	public UserRegistrationOutput registerUser(UserInput userInput) {
//
//		UserRegistrationOutput registrationOutput = new UserRegistrationOutput();
//
//		try {
//			// TODO - If user exist with the user id or contact number or email id
//
//			// validate all the fields like email, user id etc
//
//			User user = mapUserInputToModel(userInput);
//			user = userRepository.save(user);
//
//			registrationOutput.setId(user.getId());
//			registrationOutput.setSuccessCode(ErrorCodes.SUCCESS_CODE);
//			registrationOutput.setSuccessMessage("User saved successfully");
//
//			// TODO Handle excepion for all the cases
//		} catch (Exception exception) {
//			registrationOutput.setErrorCode(ErrorCodes.ERROR_CODE);
//			registrationOutput.setErrorMessage("Problem occured during save operation");
//		}
//
//		return registrationOutput;
//	}
//
//	public User mapUserInputToModel(UserInput userInput) {
//
//		User user = new User();
//		user.setFirstName(userInput.getFirstName());
//		user.setLastName(userInput.getLastName());
//		user.setContactNumber(userInput.getContactNumber());
//		user.setEmail(userInput.getEmail());
//		user.setCity(userInput.getCity());
//		user.setCountry(userInput.getCountry());
//		user.setOsVersion(userInput.getOsVersion());
//		user.setUserId(userInput.getUserId());
//		user.setPassword(userInput.getPassword());
//
//		return user;
//	}
}
