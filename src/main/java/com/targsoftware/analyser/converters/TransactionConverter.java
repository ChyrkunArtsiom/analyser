package com.targsoftware.analyser.converters;

public interface TransactionConverter<T> {
     T convertToTransactions(String inputParameter);
}
