package com.targsoftware.analyser.filters;

import com.targsoftware.analyser.entity.Transaction;

import java.util.List;

/**
 * The interface for filtering lists of {@link Transaction} objects.
 */
public interface TransactionFilter {
    /**
     * Filters list of {@link Transaction} objects.
     *
     * @param transactions the list of transactions
     * @param args         the variable arguments which can be included
     * @return the list of filtered transactions
     */
    List<Transaction> filter(List<Transaction> transactions, String... args);
}
