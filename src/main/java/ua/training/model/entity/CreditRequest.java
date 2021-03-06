package ua.training.model.entity;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreditRequest {

    public enum Status { NEW, CONFIRMED, DENIED }

    private int id;
    private int userId;
    private String userLogin;
    private Money moneyAmount;
    private LocalDateTime date;
    private Status status;

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

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public String getUserLogin() { return userLogin; }

    public void setUserLogin(String userLogin) { this.userLogin = userLogin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditRequest that = (CreditRequest) o;
        return userId == that.userId &&
                Objects.equals(userLogin, that.userLogin) &&
                Objects.equals(moneyAmount, that.moneyAmount) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userLogin, moneyAmount, date, status);
    }

    @Override
    public String toString() {
        return "CreditRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public static class CreditRequestBuilder {
        private final CreditRequest creditRequest = new CreditRequest();

        public CreditRequestBuilder setId(int id) {
            creditRequest.setId(id);
            return this;
        }

        public CreditRequestBuilder setUserId(int userId) {
            creditRequest.setUserId(userId);
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

        public CreditRequestBuilder setStatus(Status status) {
            creditRequest.setStatus(status);
            return this;
        }

        public CreditRequestBuilder setUserLogin(String userLogin) {
            creditRequest.setUserLogin(userLogin);
            return this;
        }

        public CreditRequest create() {
            return creditRequest;
        }
    }
}
