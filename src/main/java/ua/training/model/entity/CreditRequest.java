package ua.training.model.entity;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreditRequest {
    private int id;
    private int userId;
    private Money moneyAmount;
    private LocalDateTime date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Money getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Money moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditRequest that = (CreditRequest) o;
        return Objects.equals(moneyAmount, that.moneyAmount) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneyAmount, date);
    }

    @Override
    public String toString() {
        return "CreditRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                ", date=" + date +
                '}';
    }

    public static class CreditRequestBuilder {
        CreditRequest creditRequest = new CreditRequest();

        public CreditRequestBuilder setId(int id) {
            creditRequest.setId(id);
            return this;
        }

        public CreditRequestBuilder setUserId(int userId) {
            creditRequest.setId(userId);
            return this;
        }

        public CreditRequestBuilder setMoneyAmount(Money moneyAmount) {
            creditRequest.setMoneyAmount(moneyAmount);
            return this;
        }

        public CreditRequestBuilder setDate(LocalDateTime date) {
            creditRequest.setDate(date);
            return this;
        }

        public CreditRequest create() {
            return creditRequest;
        }
    }
}
