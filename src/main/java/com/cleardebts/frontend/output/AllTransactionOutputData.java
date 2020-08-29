package com.cleardebts.frontend.output;

import java.util.ArrayList;
import java.util.List;

public class AllTransactionOutputData {

	private Long totalAmountBorrowed;
	private Long totalAmountLend;

	private List<TransactionOutput> transactions = new ArrayList<TransactionOutput>();

	public Long getTotalAmountBorrowed() {
		return totalAmountBorrowed;
	}

	public void setTotalAmountBorrowed(Long totalAmountBorrowed) {
		this.totalAmountBorrowed = totalAmountBorrowed;
	}

	public Long getTotalAmountLend() {
		return totalAmountLend;
	}

	public void setTotalAmountLend(Long totalAmountLend) {
		this.totalAmountLend = totalAmountLend;
	}

	public List<TransactionOutput> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionOutput> transactions) {
		this.transactions = transactions;
	}

}
