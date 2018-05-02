package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AccountOperation {

    public enum Type { TRANSFER, BILLS_PAYMENT, LOAN_PAYMENT }

    private LocalDateTime date;
    private String recipient;
    private Type type;

    public AccountOperation() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOperation that = (AccountOperation) o;
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
        return "AccountOperation{" +
                "date=" + date +
                ", recipient='" + recipient + '\'' +
                ", type=" + type +
                '}';
    }


}
