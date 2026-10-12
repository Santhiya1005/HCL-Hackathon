package app;

import model.BankAccount;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(
                "Arun",
                "arun@example.com",
                "9876543210"
        );

        System.out.println(account.deposit(5000));
        System.out.println(account.withdraw(1000));
        System.out.println(account.withdraw(10000));

        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Accounts created: " + BankAccount.getAccountCount());
    }
}