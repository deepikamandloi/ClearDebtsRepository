package com.cleardebts.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_detail_row")
public class TransactionDetailRow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "paid_date")
	private Date paidDate = new Date();

	@Column(name = "descr_details")
	private String descrDetails;

	@Column(name = "paid_amt")
	private Long paidAmount;

	@Column(name = "updated_by_contact_no")
	private String updatedByContactNo;

	@Column(name = "row_status")
	private String rowStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_id", nullable = false)
	private Transaction transaction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public String getDescrDetails() {
		return descrDetails;
	}

	public void setDescrDetails(String descrDetails) {
		this.descrDetails = descrDetails;
	}

	public Long getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Long paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getUpdatedByContactNo() {
		return updatedByContactNo;
	}

	public void setUpdatedByContactNo(String updatedByContactNo) {
		this.updatedByContactNo = updatedByContactNo;
	}

	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}
	
	

}
