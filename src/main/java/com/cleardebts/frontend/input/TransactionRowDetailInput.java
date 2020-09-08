package com.cleardebts.frontend.input;

public class TransactionRowDetailInput {

	private Long id;

	private Long paidDate;

	private String descrDetails;

	private Long paidAmount;

	private String updatedByContactNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Long paidDate) {
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

	public String getUpdatedByContactNo() {
		return updatedByContactNo;
	}

	public void setUpdatedByContactNo(String updatedByContactNo) {
		this.updatedByContactNo = updatedByContactNo;
	}

}
