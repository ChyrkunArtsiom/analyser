package com.targsoftware.analyser.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The class for Transaction entity.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private LocalDateTime date;
    private Double amount;
    private String merchantName;
    private TransactionType transactionType;
    private String relatedTransaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
