package com.cleardebts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.TransactionInput;
import com.cleardebts.frontend.input.TransactionRowInput;
import com.cleardebts.frontend.output.AllTransactionOutput;
import com.cleardebts.frontend.output.BaseOutput;
import com.cleardebts.frontend.output.NewTransactionOutput;
import com.cleardebts.service.TransactionsService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionsService transactionService;

	@PostMapping("/")
	public ResponseEntity<NewTransactionOutput> createTransaction(@RequestBody TransactionInput transactionInput)
			throws Exception {

		NewTransactionOutput newTransactionOutput = transactionService.createTransaction(transactionInput);

		return ResponseEntity.ok(newTransactionOutput);
	}

	@GetMapping("/getAllTransactionsForUser/")
	public ResponseEntity<AllTransactionOutput> getAllTransactionsForUser() throws RecordNotFoundException {
		AllTransactionOutput getAllTransactionOutput = transactionService.getAllOpenTransactionsForUser();
		return ResponseEntity.ok(getAllTransactionOutput);
	}

	@PutMapping("/")
	public ResponseEntity<NewTransactionOutput> updateTransaction(@RequestBody TransactionInput transactionInput)
			throws Exception {

		NewTransactionOutput newTransactionOutput = transactionService.updateTransaction(transactionInput);

		return ResponseEntity.ok(newTransactionOutput);
	}

	@PutMapping("/updateTransactionRow/")
	public ResponseEntity<BaseOutput> updateTransactionRow(@RequestBody TransactionRowInput rowInput) throws Exception {

		transactionService.addTransactionRow(rowInput);
		BaseOutput baseOutput = new BaseOutput();
		baseOutput.setSuccess(true);
		baseOutput.setMessage("Row added");

		return ResponseEntity.ok(baseOutput);

//		return null
	}

	@DeleteMapping("/deleteTransactionById/{id}")
	public ResponseEntity<BaseOutput> deleteTransactionById(@PathVariable Long id) {
		return ResponseEntity.ok(transactionService.forceCloseTransaction(id));
	}

}
