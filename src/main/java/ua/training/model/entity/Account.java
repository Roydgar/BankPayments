package ua.training.model.entity;

import org.javamoney.moneta.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class Account {

    public enum Type { CREDIT, DEPOSIT, CHECKING }

    private Money balance;
    private String number;

    private LocalDateTime creationDate;
    private LocalDateTime validityDate;

    private double rate;
    private double accruedInterest;
    private Money balanceLimit;

    private Type type;

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(LocalDateTime validityDate) {
        this.validityDate = validityDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAccruedInterest() {
        return accruedInterest;
    }

    public void setAccruedInterest(double accruedInterest) {
        this.accruedInterest = accruedInterest;
    }

    public Money getBalanceLimit() {
        return balanceLimit;
    }

    public void setBalanceLimit(Money balanceLimit) {
        this.balanceLimit = balanceLimit;
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
        Account account = (Account) o;
        return Double.compare(account.rate, rate) == 0 &&
                Double.compare(account.accruedInterest, accruedInterest) == 0 &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(number, account.number) &&
                Objects.equals(creationDate, account.creationDate) &&
                Objects.equals(validityDate, account.validityDate) &&
                Objects.equals(balanceLimit, account.balanceLimit) &&
                type == account.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, number, creationDate, validityDate, rate, accruedInterest, balanceLimit, type);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", number='" + number + '\'' +
                ", creationTime=" + creationDate +
                ", validityTime=" + validityDate +
                ", rate=" + rate +
                ", accruedInterest=" + accruedInterest +
                ", balanceLimit=" + balanceLimit +
                ", type=" + type +
                '}';
    }

    public static class AccountBuilder {
        private Account account = new Account();

        public AccountBuilder() {
        }

        public AccountBuilder setBalance(Money balance) {
            account.setBalance(balance);
            return this;
        }

        public AccountBuilder setNumber(String number) {
            account.setNumber(number);
            return this;
        }

        public AccountBuilder setCreationTDate(LocalDateTime creationDate) {
            account.setCreationDate(creationDate);
            return this;
        }

        public AccountBuilder setValidityDate(LocalDateTime validityDate) {
            account.setValidityDate(validityDate);
            return this;
        }

        public AccountBuilder setRate(double rate) {
            account.setRate(rate);
            return this;
        }

        public AccountBuilder setAccruedInterest(double accruedInterest) {
            account.setAccruedInterest(accruedInterest);
            return this;
        }

        public AccountBuilder setBalanceLimit(Money balanceLimit) {
            account.setBalanceLimit(balanceLimit);
            return this;
        }

        public AccountBuilder setType(Type type) {
            account.setType(type);
            return this;
        }

        public Account create() { return account; }
    }
}
