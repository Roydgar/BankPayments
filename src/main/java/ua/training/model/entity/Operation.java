package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Operation {

    public enum Type { ACCOUNT_OPENING, TRANSFER, BILLS_PAYMENT, LOAN_PAYMENT }

    private int id;
    private int AccountId;
    private LocalDateTime date;
    private String recipient;
    private Type type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(recipient, that.recipient) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, recipient, type);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "date=" + date +
                ", recipient='" + recipient + '\'' +
                ", type=" + type +
                '}';
    }

    public static class OperationBuilder {
        Operation operation = new Operation();

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

        public Operation create() {
            return operation;
        }
    }

}
