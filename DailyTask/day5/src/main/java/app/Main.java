
package app;

abstract class Payment {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();

    public void showAmount() {
        System.out.println("Payment Amount: Rs. " + amount);
    }
}

class CardPayment extends Payment {

    public CardPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing card payment...");
        showAmount();
    }
}

class UPIPayment extends Payment {

    public UPIPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment...");
        showAmount();
    }
}

class CashPayment extends Payment {

    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void processPayment() {
        System.out.println("Processing cash payment...");
        showAmount();
    }
}

public class Main {

    public static void main(String[] args) {

        Payment payment1 = new CardPayment(1500);
        Payment payment2 = new UPIPayment(800);
        Payment payment3 = new CashPayment(500);

        payment1.processPayment();
        payment2.processPayment();
        payment3.processPayment();
    }
}