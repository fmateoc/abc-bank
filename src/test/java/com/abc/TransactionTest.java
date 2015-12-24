package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(BigDecimal.TEN);
        assertTrue(t instanceof Transaction);
    }
}
