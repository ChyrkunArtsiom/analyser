package com.targsoftware.analyser.reporters.impl;

import com.targsoftware.analyser.entity.Transaction;
import com.targsoftware.analyser.entity.TransactionType;
import com.targsoftware.analyser.reporters.TransactionFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The implementation of {@link TransactionFilter} which filters by datetime and merchant name.
 */
public class DateRangeAndMerchantNameTransactionFilter implements TransactionFilter {
    @Override
    public List<Transaction> filter(List<Transaction> transactions, String... args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime fromDate = LocalDateTime.parse(args[0], df);
        LocalDateTime toDate = LocalDateTime.parse(args[1], df);
        String merchant = args[2];

        // Getting a list of related transactions that used in reversal transactions
        List<String> reversalTransactions = transactions
                .stream()
                .filter(t -> t.getTransactionType() == TransactionType.REVERSAL)
                .map(Transaction::getRelatedTransaction).collect(Collectors.toList());

        // Filtering a list of non-related transactions in a specific time range and with a specific name
        transactions = transactions
                .stream()
                .filter(transaction ->
                        transaction.getDate().isAfter(fromDate)
                                && transaction.getDate().isBefore(toDate)
                                && transaction.getMerchantName().equals(merchant)
                                && !reversalTransactions.contains(transaction.getId()))
                .collect(Collectors.toList());
        return transactions;
    }
}
