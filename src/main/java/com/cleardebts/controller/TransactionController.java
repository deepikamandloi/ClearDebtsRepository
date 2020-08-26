package com.cleardebts.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.UserInput;
import com.cleardebts.frontend.output.AllTransactionOutput;
import com.cleardebts.frontend.output.AllTransactionOutputData;
import com.cleardebts.frontend.output.TransactionOutput;
import com.cleardebts.frontend.output.UserRegistrationOutput;
import com.cleardebts.util.ParticipentType;
import com.cleardebts.util.TransactionStatus;
import com.cleardebts.util.TransactionType;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	@PostMapping("/")
	public ResponseEntity<UserRegistrationOutput> createTransaction(@RequestBody UserInput userInput) throws Exception {

//		UserRegistrationOutput registrationOutput;
//
//		try {
//			registrationOutput = userService.registerUser(userInput);
//
//		} catch (ClearDebtsException cde) {
//			registrationOutput = new UserRegistrationOutput();
//			registrationOutput.setSuccess(false);
//			registrationOutput.setMessage(cde.getMessage());
//			registrationOutput.setErrorCode(cde.getErrorCode());
//		}
		return ResponseEntity.ok(null);
	}

	@GetMapping("/getAllTransactionsForUser/{id}")
	public ResponseEntity<AllTransactionOutput> getAllTransactionsForUser(@PathVariable Long id)
			throws RecordNotFoundException {
		AllTransactionOutput getAllTransactionOutput = createTempOutput();
		return ResponseEntity.ok(getAllTransactionOutput);
	}

	private AllTransactionOutput createTempOutput() {

		AllTransactionOutput allTransactionOutput = new AllTransactionOutput();
		AllTransactionOutputData allTransactionOutputData = new AllTransactionOutputData();

		TransactionOutput output1 = new TransactionOutput();
		output1.setId(1L);
		output1.setAmount(151L);
		output1.setDueDate(new Date());
		output1.setFromName("Saleel");
		output1.setToName("Ashish");
		output1.setParticipentType(ParticipentType.INDIVIDUAL_TRANSACTION);
		output1.setStatus(TransactionStatus.OPEN);
		output1.setTransactionDate(new Date());
		output1.setType(TransactionType.TR_LEND);

		TransactionOutput output2 = new TransactionOutput();
		output2.setId(2L);
		output2.setAmount(1001L);
		output2.setDueDate(new Date());
		output2.setFromName("Saleel");
		output2.setToName("Gaurav");
		output2.setParticipentType(ParticipentType.INDIVIDUAL_TRANSACTION);
		output2.setStatus(TransactionStatus.OPEN);
		output2.setTransactionDate(new Date());
		output2.setType(TransactionType.TR_BORROW);

		TransactionOutput output3 = new TransactionOutput();
		output3.setId(3L);
		output3.setAmount(551L);
		output3.setDueDate(new Date());
		output3.setFromName("Saleel");
		output3.setToName("Ankush");
		output3.setParticipentType(ParticipentType.INDIVIDUAL_TRANSACTION);
		output3.setStatus(TransactionStatus.OPEN);
		output3.setTransactionDate(new Date());
		output3.setType(TransactionType.TR_BORROW);

		List<TransactionOutput> transactionOutputs = new ArrayList<TransactionOutput>();
		transactionOutputs.add(output1);
		transactionOutputs.add(output2);
		transactionOutputs.add(output3);

		allTransactionOutputData.setTotalAmountBorrowed(1200L);
		allTransactionOutputData.setTotalAmountLend(300L);
		allTransactionOutputData.setTransactions(transactionOutputs);

		allTransactionOutput.setMessage("Success");
		allTransactionOutput.setSuccess(true);
		allTransactionOutput.setTransactionOutputData(allTransactionOutputData);

		return allTransactionOutput;

	}
}
