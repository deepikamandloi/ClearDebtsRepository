package com.cleardebts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleardebts.frontend.input.TransactionInput;
import com.cleardebts.frontend.output.AllTransactionOutput;
import com.cleardebts.frontend.output.AllTransactionOutputData;
import com.cleardebts.frontend.output.NewTransactionOutput;
import com.cleardebts.frontend.output.TransactionOutput;
import com.cleardebts.model.Transaction;
import com.cleardebts.model.User;
import com.cleardebts.repository.TransactionRepository;
import com.cleardebts.util.DateUtil;
import com.cleardebts.util.ParticipentType;
import com.cleardebts.util.TransactionStatus;
import com.cleardebts.util.TransactionType;

@Service
public class TransactionsService {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository transactionRepository;

	public NewTransactionOutput createTransaction(TransactionInput transactionInput) {

		NewTransactionOutput newTransactionOutput = new NewTransactionOutput();

		try {
			Transaction transaction = mapTransactionInputToModel(transactionInput);
			transaction = transactionRepository.save(transaction);

			newTransactionOutput.getData().setId(transaction.getId());
			newTransactionOutput.setMessage("Transaction created successfully!!");
			newTransactionOutput.setSuccess(true);

		} catch (Exception e) {
//			newTransactionOutput.getData().setId(transaction.getId());
			newTransactionOutput.setMessage("Problem occurred!!");
			newTransactionOutput.setSuccess(false);
		}

		return newTransactionOutput;
	}

	private Transaction mapTransactionInputToModel(TransactionInput transactionInput) {

		Transaction transaction = new Transaction();
		transaction.setBorrowerContact(transactionInput.getBorrowerContactNumber());
		transaction.setDescr(transactionInput.getDescription());
		transaction.setDueDate(DateUtil.getDateFromUnixTimestamp(transactionInput.getDueDate()));
		transaction.setFromName(transactionInput.getFromName());
		transaction.setInitiatorUser(userService.getCurrentUser());

		if (transactionInput.getParticipentType().equals(ParticipentType.GROUP_TRANSACTION)) {
			transaction.setIsGroupTransaction(true);
		} else {
			transaction.setIsGroupTransaction(false);
		}

		transaction.setLenderContact(transactionInput.getLenderContactNumber());
		transaction.setOriginalAmount(transactionInput.getOriginalAmount());
		transaction.setPendingAmount(transactionInput.getOriginalAmount());
		transaction.setStatus(TransactionStatus.OPEN.name());
		transaction.setToName(transactionInput.getToName());
		transaction.setTransactionDate(DateUtil.getDateFromUnixTimestamp(transactionInput.getTransactionDate()));
		transaction.setType(transactionInput.getType().name());

		return transaction;

	}

	public AllTransactionOutput getAllOpenTransactionsForUser() {

		AllTransactionOutput allTransactionOutput = new AllTransactionOutput();
		AllTransactionOutputData allTransactionOutputData = allTransactionOutput.getTransactionOutputData();
		Long totalAmountBorrowed = 0L;
		Long totalAmountLend = 0L;

		User user = userService.getCurrentUser();

		List<Transaction> openTransactions = transactionRepository.getOpenTransactionForUser(user.getContactNumber(),
				TransactionStatus.OPEN.name());

		for (Transaction transaction : openTransactions) {

			if (user.getContactNumber().equals(transaction.getBorrowerContact())) {
				totalAmountBorrowed = totalAmountBorrowed + transaction.getPendingAmount();
			} else {
				totalAmountLend = totalAmountLend + transaction.getPendingAmount();
			}
			allTransactionOutputData.getTransactions().add(mapModelToTransactionOutput(transaction));

		}

		allTransactionOutputData.setTotalAmountBorrowed(totalAmountBorrowed);
		allTransactionOutputData.setTotalAmountLend(totalAmountLend);
		allTransactionOutput.setMessage("Success");
		allTransactionOutput.setSuccess(true);
		return allTransactionOutput;

	}

	private TransactionOutput mapModelToTransactionOutput(Transaction transaction) {

		TransactionOutput transactionOutput = new TransactionOutput();

		transactionOutput.setDueDate(transaction.getDueDate().getTime());
		transactionOutput.setFromName(transaction.getFromName());
		transactionOutput.setId(transaction.getId());
		transactionOutput.setOriginalAmount(transaction.getOriginalAmount());
		transactionOutput.setPendingAmount(transaction.getOriginalAmount());
		if (transaction.getIsGroupTransaction()) {
			transactionOutput.setParticipentType(ParticipentType.GROUP_TRANSACTION);
		} else {
			transactionOutput.setParticipentType(ParticipentType.INDIVIDUAL_TRANSACTION);
		}

		transactionOutput.setStatus(TransactionStatus.OPEN);
		transactionOutput.setToName(transaction.getToName());
		transactionOutput.setTransactionDate(transaction.getTransactionDate().getTime());
		transactionOutput.setType(TransactionType.valueOf(transaction.getType()));
		transactionOutput.setDescription(transaction.getDescr());

		transactionOutput.setBorrowerContactNumber(transaction.getBorrowerContact());
		transactionOutput.setLenderContactNumber(transaction.getLenderContact());

		return transactionOutput;
	}

}
