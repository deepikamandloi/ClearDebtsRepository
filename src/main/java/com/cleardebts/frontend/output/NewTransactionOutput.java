package com.cleardebts.frontend.output;

public class NewTransactionOutput extends BaseOutput {

	private NewTransactionOutputData data = new NewTransactionOutputData();

	public NewTransactionOutputData getData() {
		return data;
	}

	public void setData(NewTransactionOutputData data) {
		this.data = data;
	}

}
