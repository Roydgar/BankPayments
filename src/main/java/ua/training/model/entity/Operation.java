package ua.training.model.entity;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class Operation {

    public enum Type { TRANSFER, LOAN_PAYMENT }

    private int id;
    private int AccountId;
    private LocalDateTime date;
    private String recipient;
    private Type type;
    private Money moneyAmount;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getAccountId() { return AccountId; }

    public void setAccountId(int accountId) { AccountId = accountId; }

    public Money getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Money moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return AccountId == operation.AccountId &&
                Objects.equals(date, operation.date) &&
                Objects.equals(recipient, operation.recipient) &&
                type == operation.type &&
                Objects.equals(moneyAmount, operation.moneyAmount);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", AccountId=" + AccountId +
                ", date=" + date +
                ", recipient='" + recipient + '\'' +
                ", type=" + type +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(AccountId, date, recipient, type, moneyAmount);
    }

    public static class OperationBuilder {
        private final Operation operation = new Operation();

        public OperationBuilder setId(int id) {
            operation.setId(id);
            return this;
        }

        public OperationBuilder setAccountId(int accountId) {
            operation.setAccountId(accountId);
            return this;
        }

        public OperationBuilder setDate(LocalDateTime date) {
            operation.setDate(date);
            return this;
        }

        public OperationBuilder setRecipient(String recipient) {
            operation.setRecipient(recipient);
            return this;
        }

        public OperationBuilder setType(Type type) {
            operation.setType(type);
            return this;
        }

        public OperationBuilder setMoneyAmount(Money money) {
            operation.setMoneyAmount(money);
            return this;
        }

        public Operation create() {
            return operation;
        }
    }

}
