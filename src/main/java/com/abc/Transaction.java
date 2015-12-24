package com.abc;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    public final BigDecimal amount;

    public final Date transactionDate;

    public Transaction(BigDecimal amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}
