package com.cleardebts.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "transaction_date")
	private Date transactionDate = new Date();

	@Column(name = "due_date")
	private Date dueDate = new Date();

	@Column(name = "descr")
	private String descr;

	@Column(name = "type")
	private String type;

	@Column(name = "from_name")
	private String fromName;

	@Column(name = "to_name")
	private String toName;

	@Column(name = "pending_amt")
	private Long pendingAmount;

	@Column(name = "original_amount")
	private Long originalAmount;

	@Column(name = "is_group_transaction")
	private Boolean isGroupTransaction;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "transaction")
	private Set<TransactionDetailRow> transactionDetailRows = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "initiator_user_id", nullable = false)
	private User initiatorUser;

	@Column(name = "borrower_contact")
	private String borrowerContact;

	@Column(name = "lender_contact")
	private String lenderContact;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public Long getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Long pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public Long getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Long originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Boolean getIsGroupTransaction() {
		return isGroupTransaction;
	}

	public void setIsGroupTransaction(Boolean isGroupTransaction) {
		this.isGroupTransaction = isGroupTransaction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Set<TransactionDetailRow> getTransactionDetailRows() {
		return transactionDetailRows;
	}

	public void setTransactionDetailRows(Set<TransactionDetailRow> transactionDetailRows) {
		this.transactionDetailRows = transactionDetailRows;
	}

	public User getInitiatorUser() {
		return initiatorUser;
	}

	public void setInitiatorUser(User initiatorUser) {
		this.initiatorUser = initiatorUser;
	}

	public String getBorrowerContact() {
		return borrowerContact;
	}

	public void setBorrowerContact(String borrowerContact) {
		this.borrowerContact = borrowerContact;
	}

	public String getLenderContact() {
		return lenderContact;
	}

	public void setLenderContact(String lenderContact) {
		this.lenderContact = lenderContact;
	}

	
}
