package com.targsoftware.analyser.converters.impl;

import com.targsoftware.analyser.converters.TransactionConverter;
import com.targsoftware.analyser.entity.Transaction;
import com.targsoftware.analyser.entity.TransactionType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVToTransactionConverter implements TransactionConverter<List<Transaction>> {
    private final static String[] HEADERS = {"ID", "Date", "Amount", "Merchant", "Type", "Related Transaction"};

    @Override
    public List<Transaction> convertToTransactions(String inputParameter) {
        List<Transaction> transactions = new ArrayList<>();
        try (Reader in = new FileReader(inputParameter)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader(HEADERS)
                    .withFirstRecordAsHeader()
                    .withIgnoreSurroundingSpaces()
                    .parse(in);
            for (CSVRecord record : records) {
                transactions.add(convertCSVRecordToTransaction(record));
            }
            return transactions;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(String.format("File at path %s doesn't exist", inputParameter));
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Converter cannot parse file at %s", inputParameter));
        }
    }

    private Transaction convertCSVRecordToTransaction(CSVRecord record) {
        String id = record.get("ID");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(record.get("Date"), df);
        Double amount = Double.valueOf(record.get("Amount"));
        String merchantName = record.get("Merchant");
        TransactionType transactionType = TransactionType.valueOf(record.get("Type"));
        String relatedTransaction = record.get("Related Transaction");
        return new Transaction(id, date, amount, merchantName, transactionType, relatedTransaction);
    }
}
