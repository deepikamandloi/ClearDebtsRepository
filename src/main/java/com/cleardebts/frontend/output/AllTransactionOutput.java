package com.cleardebts.frontend.output;

public class AllTransactionOutput extends BaseOutput {

	private AllTransactionOutputData data = new AllTransactionOutputData();

	public AllTransactionOutputData getTransactionOutputData() {
		return data;
	}

	public void setTransactionOutputData(AllTransactionOutputData data) {
		this.data = data;
	}

}
