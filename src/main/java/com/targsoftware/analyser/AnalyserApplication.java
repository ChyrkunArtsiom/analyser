package com.targsoftware.analyser;

import com.targsoftware.analyser.converters.impl.CSVToTransactionConverter;
import com.targsoftware.analyser.entity.Transaction;
import com.targsoftware.analyser.reporters.impl.DateRangeAndMerchantNameTransactionFilter;

import java.util.List;
import java.util.Scanner;

public class AnalyserApplication {
    public static void main(String[] args) {
        String filename = "data.csv";
        CSVToTransactionConverter converter = new CSVToTransactionConverter();
        DateRangeAndMerchantNameTransactionFilter reporter = new DateRangeAndMerchantNameTransactionFilter();
        Scanner scanner = new Scanner(System.in);
        System.out.print("fromDate:");
        String fromDate = scanner.nextLine();
        System.out.print("toDate:");
        String toDate = scanner.nextLine();
        System.out.print("merchant:");
        String merchant = scanner.nextLine();
        List<Transaction> transactions = converter.convertToTransactions(filename);
        transactions = reporter.filter(transactions, fromDate, toDate, merchant);
        Double average = transactions.stream().mapToDouble(Transaction::getAmount).average().orElse(Double.NaN);
        System.out.printf("Number of transactions = %s%n", transactions.size());
        System.out.printf("Average Transaction Value = %.2f%n", average);
    }
}
