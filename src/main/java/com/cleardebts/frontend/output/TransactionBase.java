package com.cleardebts.frontend.output;

import com.cleardebts.util.ParticipentType;
import com.cleardebts.util.TransactionStatus;
import com.cleardebts.util.TransactionType;

public class TransactionBase {

	private Long id;
	private Long transactionDate;
	private Long dueDate;
	private TransactionType type;
	private String fromName;
	private String toName;
	private Long originalAmount;
	private Long pendingAmount;
	private ParticipentType participentType;
	private TransactionStatus status;
	private String borrowerContactNumber;
	private String lenderContactNumber;
	private String description;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getDueDate() {
		return dueDate;
	}

	public void setDueDate(Long dueDate) {
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

	public Long getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Long originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Long getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Long pendingAmount) {
		this.pendingAmount = pendingAmount;
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

	public String getBorrowerContactNumber() {
		return borrowerContactNumber;
	}

	public void setBorrowerContactNumber(String borrowerContactNumber) {
		this.borrowerContactNumber = borrowerContactNumber;
	}

	public String getLenderContactNumber() {
		return lenderContactNumber;
	}

	public void setLenderContactNumber(String lenderContactNumber) {
		this.lenderContactNumber = lenderContactNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descr) {
		this.description = descr;
	}

	
}
