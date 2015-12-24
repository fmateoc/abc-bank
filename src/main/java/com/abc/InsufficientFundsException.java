package com.abc;

import java.math.BigDecimal;

public class InsufficientFundsException extends Exception {

	private Account account;
	private BigDecimal requestedAmount;
	
	public InsufficientFundsException(BigDecimal amount, Account account) {
		this.requestedAmount = amount;
		this.account = account;
	}

}
