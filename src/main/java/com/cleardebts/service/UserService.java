package com.cleardebts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cleardebts.exception.ClearDebtsException;
import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.UserInput;
import com.cleardebts.frontend.output.UserOutput;
import com.cleardebts.frontend.output.UserOutputData;
import com.cleardebts.frontend.output.UserRegistrationOutput;
import com.cleardebts.model.User;
import com.cleardebts.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserRegistrationOutput registerUser(UserInput userInput) throws ClearDebtsException {

		UserRegistrationOutput registrationOutput = new UserRegistrationOutput();

		try {
			// TODO - If user exist with the user id or contact number or email id
			validateContactAndEmailDetails(userInput.getContactNumber(), userInput.getEmail());

			// validate all the fields like email, user id etc

			User user = mapUserInputToModel(userInput);
			user = userRepository.save(user);

			registrationOutput.getData().setId(user.getId());
			registrationOutput.setSuccess(true);
			registrationOutput.setMessage("User saved successfully");

			// TODO Handle excepion for all the cases
		} catch (ClearDebtsException cde) {
			throw cde;
		} catch (Exception exception) {
			registrationOutput.setSuccess(false);
			registrationOutput.setMessage("Problem occured during save operation");
		}

		return registrationOutput;
	}

	private void validateContactAndEmailDetails(final String contactNumber, final String email)
			throws ClearDebtsException {

		User user = userRepository.getUserByContactAndEmail(contactNumber, email);

		if (user != null)
			throw new ClearDebtsException("User already exist", HttpStatus.CONFLICT.value());

	}

	public User mapUserInputToModel(UserInput userInput) {

		User user = new User();
		user.setFirstName(userInput.getFirstName());
		user.setLastName(userInput.getLastName());
		user.setContactNumber(userInput.getContactNumber());
		user.setEmail(userInput.getEmail());
		user.setCity(userInput.getCity());
		user.setCountry(userInput.getCountry());
		user.setOsVersion(userInput.getOsVersion());
		user.setPassword(userInput.getPassword());

		return user;
	}

	public UserInput getUserById(Long id) throws RecordNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new RecordNotFoundException("User not found exception");
		}

		return mapModelToUserInput(user.get());
	}

	public UserOutputData getUserByUserName(final String userName) throws RecordNotFoundException {

		User user = userRepository.getUserByContactAndEmail(userName, userName);

		if (user == null) {
			throw new RecordNotFoundException("User not found exception");
		}

		UserOutputData data = new UserOutputData();

		UserOutput userOutput = new UserOutput();
		userOutput.setFirstName(user.getFirstName());
		userOutput.setLastName(user.getLastName());
		userOutput.setContactNumber(user.getContactNumber());
		userOutput.setEmail(user.getEmail());
		userOutput.setCity(user.getCity());
		userOutput.setCountry(user.getCountry());
		userOutput.setOsVersion(user.getOsVersion());

		data.setUser(userOutput);

		return data;
	}

	public UserInput mapModelToUserInput(User user) {

		UserInput userInput = new UserInput();
		userInput.setFirstName(user.getFirstName());
		userInput.setLastName(user.getLastName());
		userInput.setContactNumber(user.getContactNumber());
		userInput.setEmail(user.getEmail());
		userInput.setCity(user.getCity());
		userInput.setCountry(user.getCountry());
		userInput.setOsVersion(user.getOsVersion());
		userInput.setPassword(user.getPassword());

		return userInput;
	}

}
