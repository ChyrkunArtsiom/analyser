package com.targsoftware.analyser.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The class for Transaction entity.
 */
public class Transaction {
    private String id;
    private LocalDateTime date;
    private Double amount;
    private String merchantName;
    private TransactionType transactionType;
    private String relatedTransaction;

    /**
     * Empty constructor.
     */
    public Transaction(){

    }

    /**
     * Constructor with all fields.
     *
     * @param id                 the id
     * @param date               the date
     * @param amount             the amount
     * @param merchantName       the merchant name
     * @param transactionType    the transaction type
     * @param relatedTransaction the related transaction
     */
    public Transaction(String id,
                       LocalDateTime date,
                       Double amount,
                       String merchantName,
                       TransactionType transactionType,
                       String relatedTransaction) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.merchantName = merchantName;
        this.transactionType = transactionType;
        this.relatedTransaction = relatedTransaction;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets merchant name.
     *
     * @return the merchant name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Sets merchant name.
     *
     * @param merchantName the merchant name
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets transaction type.
     *
     * @param transactionType the transaction type
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Gets related transaction.
     *
     * @return the related transaction
     */
    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    /**
     * Sets related transaction.
     *
     * @param relatedTransaction the related transaction
     */
    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", merchantName='" + merchantName + '\'' +
                ", transactionType=" + transactionType +
                ", relatedTransaction='" + relatedTransaction + '\'' +
                '}';
    }

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
