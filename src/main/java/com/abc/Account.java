package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(BigDecimal amount) {
        if (amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void withdraw(BigDecimal amount) throws InsufficientFundsException {
    if (amount.signum() <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
    	if(this.sumTransactions().compareTo(amount) > 0)
    		transactions.add(new Transaction(amount.negate()));
    	else
    		throw new InsufficientFundsException(amount, this);
    }
}

    public BigDecimal interestEarned() {
        BigDecimal amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0)
                    return amount.multiply(BigDecimal.valueOf(0.001));
                else
                    return amount.subtract(BigDecimal.valueOf(1000)).multiply(BigDecimal.valueOf(0.002)).add(BigDecimal.ONE);
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0)
                    return amount.multiply(BigDecimal.valueOf(0.02));
                if (amount.compareTo(BigDecimal.valueOf(2000)) <= 0)
                    return amount.subtract(BigDecimal.valueOf(1000)).multiply(BigDecimal.valueOf(0.05)).add(BigDecimal.valueOf(20));
                return amount.subtract(BigDecimal.valueOf(2000)).multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(70));
            default:
                return amount.multiply(BigDecimal.valueOf(0.001));
        }
    }

    public BigDecimal sumTransactions() {
    	BigDecimal amount = BigDecimal.ZERO;
        for (Transaction t: transactions)
            amount = amount.add(t.amount);
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

	public void transferTo(Account anotherAccount, BigDecimal amount) throws InsufficientFundsException {
	    if (anotherAccount == this) {
	        throw new IllegalArgumentException("cannot transfer to the same account");
	    } else {
	    	withdraw(amount);
    		anotherAccount.deposit(amount);
	    }
	}

}
