package com.cleardebts.frontend.input;

import java.util.ArrayList;
import java.util.List;

public class TransactionRowInput {

	private Long transactionId;

	private List<TransactionRowDetailInput> rows = new ArrayList<>();

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public List<TransactionRowDetailInput> getRows() {
		return rows;
	}

	public void setRows(List<TransactionRowDetailInput> rows) {
		this.rows = rows;
	}

}
