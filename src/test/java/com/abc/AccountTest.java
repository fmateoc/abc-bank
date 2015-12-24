package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testSuccessfulWithdrawal() throws InsufficientFundsException {
        Account checkingAccount = new Account(Account.CHECKING);

        checkingAccount.deposit(BigDecimal.valueOf(100));
        checkingAccount.withdraw(BigDecimal.valueOf(50));

        assertEquals(50.0, checkingAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testUnsuccessfulWithdrawal() throws InsufficientFundsException {
        Account checkingAccount = new Account(Account.CHECKING);

        checkingAccount.withdraw(BigDecimal.valueOf(50));

        fail();
    }

    @Test
    public void testSuccessfulTransfer() throws InsufficientFundsException {
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        checkingAccount.deposit(BigDecimal.valueOf(100));
        savingsAccount.deposit(BigDecimal.valueOf(4000));
        savingsAccount.transferTo(checkingAccount, BigDecimal.valueOf(50));

        assertEquals(150.0, checkingAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
        assertEquals(3950.0, savingsAccount.sumTransactions().doubleValue(), DOUBLE_DELTA);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testUnsuccessfulTransfer() throws InsufficientFundsException {
        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        checkingAccount.deposit(BigDecimal.valueOf(100));
        savingsAccount.deposit(BigDecimal.valueOf(4000));
        checkingAccount.transferTo(savingsAccount, BigDecimal.valueOf(500));
        
        fail();
    }

}
