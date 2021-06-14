package com.targsoftware.analyser.converters.impl;

import com.targsoftware.analyser.entity.Transaction;
import com.targsoftware.analyser.entity.TransactionType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class CSVToTransactionConverterTest {
    private final static String[] HEADERS = {"ID", "Date", "Amount", "Merchant", "Type", "Related Transaction"};
    private List<Transaction> transactions;
    private final static String FILEPATH = "test.csv";

    @BeforeEach
    void setUp() {
        try (FileWriter out = new FileWriter(FILEPATH);
             CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS))
        ) {
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

            transactions.forEach(transaction -> {
                try {
                    printer.printRecord(
                            transaction.getId(),
                            transaction.getDate().format(df),
                            transaction.getAmount(),
                            transaction.getMerchantName(),
                            transaction.getTransactionType(),
                            transaction.getRelatedTransaction());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        Path path = Paths.get(FILEPATH);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConvertToTransactions() {
        CSVToTransactionConverter converter = new CSVToTransactionConverter();
        List<Transaction> converted = converter.convertToTransactions(FILEPATH);
        Assertions.assertEquals(transactions, converted);
    }
}