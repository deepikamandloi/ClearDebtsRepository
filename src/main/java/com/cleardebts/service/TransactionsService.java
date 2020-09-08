package com.cleardebts.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleardebts.exception.RecordNotFoundException;
import com.cleardebts.frontend.input.TransactionInput;
import com.cleardebts.frontend.input.TransactionRowDetailInput;
import com.cleardebts.frontend.input.TransactionRowInput;
import com.cleardebts.frontend.output.AllTransactionOutput;
import com.cleardebts.frontend.output.AllTransactionOutputData;
import com.cleardebts.frontend.output.BaseOutput;
import com.cleardebts.frontend.output.NewTransactionOutput;
import com.cleardebts.frontend.output.TransactionOutput;
import com.cleardebts.model.Transaction;
import com.cleardebts.model.TransactionDetailRow;
import com.cleardebts.model.User;
import com.cleardebts.repository.TransactionRepository;
import com.cleardebts.repository.TransactionRowDetailRepository;
import com.cleardebts.util.DateUtil;
import com.cleardebts.util.ErrorCodes;
import com.cleardebts.util.ParticipentType;
import com.cleardebts.util.TransactionStatus;
import com.cleardebts.util.TransactionType;

@Service
public class TransactionsService {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionRowDetailRepository rowDetailRepository;

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

	// Only a few fields can be updated
	public NewTransactionOutput updateTransaction(TransactionInput transactionInput) throws RecordNotFoundException {

		NewTransactionOutput newTransactionOutput = new NewTransactionOutput();

		try {
			Transaction transaction = transactionRepository.getOne(transactionInput.getId());

			if (transaction == null)
				throw new RecordNotFoundException("Transaction not found");

			transaction.setDescr(transactionInput.getDescription());
			transaction.setDueDate(DateUtil.getDateFromUnixTimestamp(transactionInput.getDueDate()));

			transactionRepository.save(transaction);
			newTransactionOutput.getData().setId(transaction.getId());
			newTransactionOutput.setMessage("Transaction created successfully!!");
			newTransactionOutput.setSuccess(true);
		} catch (Exception e) {
			newTransactionOutput.setMessage("Problem occurred!!");
			newTransactionOutput.setSuccess(false);
		}

		return newTransactionOutput;
	}

	public BaseOutput forceCloseTransaction(Long id) {

		// TODO: It can be deleted by initiator only
		BaseOutput baseOutput = new BaseOutput();
		try {
			Transaction transaction = transactionRepository.getOne(id);
			if (transaction == null)
				throw new RecordNotFoundException("Transaction not found");

			transaction.setStatus(TransactionStatus.FORCE_CLOSED.name());
			transactionRepository.save(transaction);

		} catch (RecordNotFoundException recordNotFoundException) {
			baseOutput.setErrorCode(ErrorCodes.RECORD_NOT_FOUND_CODE.intValue());
			baseOutput.setMessage(recordNotFoundException.getMessage());
			baseOutput.setSuccess(false);
		} catch (Exception e) {
			baseOutput.setErrorCode(ErrorCodes.ERROR_CODE.intValue());
			baseOutput.setMessage("Problem occured");
			baseOutput.setSuccess(false);
		}
		return baseOutput;

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
		transactionOutput.setPendingAmount(transaction.getPendingAmount());
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

	@Transactional
	public void addTransactionRow(TransactionRowInput transactionInput) {

		try {

			Transaction transaction = transactionRepository.getOne(transactionInput.getTransactionId());

			Long totalTransAmt = 0L;

			if (transaction == null)
				throw new RecordNotFoundException();

			if (transactionInput.getRows() != null && !transactionInput.getRows().isEmpty()) {
				for (TransactionRowDetailInput input : transactionInput.getRows()) {

					TransactionDetailRow detailRow = new TransactionDetailRow();
					detailRow.setTransaction(transaction);
					detailRow.setDescrDetails(input.getDescrDetails());
					detailRow.setPaidAmount(input.getPaidAmount());
					detailRow.setRowStatus(TransactionStatus.OPEN.name());
					detailRow.setUpdatedByContactNo(userService.getCurrentUser().getContactNumber());
					detailRow = rowDetailRepository.save(detailRow);
					System.out.println("Saved detailRow : "+ detailRow.getId());
					totalTransAmt = totalTransAmt + input.getPaidAmount();

				}
			}

			transaction.setPendingAmount(transaction.getPendingAmount() - totalTransAmt);
			transactionRepository.save(transaction);
			System.out.println("Saved");

		} catch (RecordNotFoundException recordNotFoundException) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
