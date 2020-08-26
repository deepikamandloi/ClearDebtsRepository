package com.cleardebts.frontend.output;

import java.util.Date;

import com.cleardebts.util.ParticipentType;
import com.cleardebts.util.TransactionStatus;
import com.cleardebts.util.TransactionType;

public class TransactionOutput {

	private Long id;
	private Date transactionDate = new Date();
	private Date dueDate = new Date();
	private TransactionType type;
	private String fromName;
	private String toName;
	private Long amount;
	private ParticipentType participentType;
	private TransactionStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public ParticipentType getParticipentType() {
		return participentType;
	}

	public void setParticipentType(ParticipentType participentType) {
		this.participentType = participentType;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
	}

}
