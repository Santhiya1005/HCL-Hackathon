package model;

import java.util.Objects;

public class BankAccount {

    private String name;
    private String email;
    private String phNo;
    private String accType;
    private double balance;
    private String creationDate;

    private static int accountCount = 0;

    public BankAccount() {
        this("Unknown", "unknown@example.com", "0000000000");
    }

    public BankAccount(String name, String email, String phNo) {
        this(name, email, phNo, "Savings", 0.0, "Not specified");
    }

    public BankAccount(String name, String email, String phNo,
                       String accType, double balance, String creationDate) {

        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.name = name;
        this.email = email;
        this.phNo = phNo;
        this.accType = accType;
        this.balance = balance;
        this.creationDate = creationDate;

        accountCount++;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhNo() {
        return phNo;
    }

    public String getAccType() {
        return accType;
    }

    public double getBalance() {
        return balance;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public static int getAccountCount() {
        return accountCount;
    }

    public String deposit(double amount) {
        if (amount <= 0) {
            return "Invalid deposit amount";
        }

        balance += amount;
        return "Deposit successful. Balance: " + balance;
    }

    public String withdraw(double amount) {
        if (amount <= 0) {
            return "Invalid withdrawal amount";
        }

        if (amount > balance) {
            return "Insufficient balance";
        }

        balance -= amount;
        return "Withdrawal successful. Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BankAccount)) {
            return false;
        }

        BankAccount other = (BankAccount) obj;

        return Objects.equals(email, other.email)
                && Objects.equals(phNo, other.phNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phNo);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accType='" + accType + '\'' +
                ", balance=" + balance +
                '}';
    }
}