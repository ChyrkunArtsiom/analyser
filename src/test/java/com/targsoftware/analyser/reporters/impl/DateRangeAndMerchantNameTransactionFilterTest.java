package com.targsoftware.analyser.reporters.impl;

import com.targsoftware.analyser.entity.Transaction;
import com.targsoftware.analyser.entity.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class DateRangeAndMerchantNameTransactionFilterTest {
    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        transactions = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        transactions.add(
                new Transaction(
                        "YGXKOEIA",
                        LocalDateTime.parse("20/08/2018 12:46:17", df),
                        Double.valueOf("10.95"),
                        "Kwik-E-Mart",
                        TransactionType.PAYMENT,
                        null));
        transactions.add(
                new Transaction(
                        "LFVCTEYM",
                        LocalDateTime.parse("20/08/2018 12:50:02", df),
                        Double.valueOf("5.00"),
                        "MacLaren",
                        TransactionType.PAYMENT,
                        null));
        transactions.add(
                new Transaction(
                        "AKNBVHMN",
                        LocalDateTime.parse("20/08/2018 13:01:02", df),
                        Double.valueOf("11.00"),
                        "Kwik-E-Mart",
                        TransactionType.PAYMENT,
                        null));
    }

    @Test
    void testReport() {
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "20/08/2018 13:02:00";
        String merchant = "Kwik-E-Mart";
        DateRangeAndMerchantNameTransactionFilter filter = new DateRangeAndMerchantNameTransactionFilter();
        List<Transaction> filtered = filter.filter(transactions, fromDate, toDate, merchant);
        Assertions.assertEquals(transactions.get(0), filtered.get(0));
        Assertions.assertEquals(transactions.get(2), filtered.get(1));
    }
}