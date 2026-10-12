package app;

import exception.InsufficientStockException;
import exception.InvalidQuantityException;

public class OrderProcessor {

    private int availableStock = 10;

    public void processOrder(int quantity)
            throws InsufficientStockException {

        System.out.println("\nProcessing order for " + quantity + " units...");

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero."
            );
        }

        if (quantity > availableStock) {
            throw new InsufficientStockException(
                    "Requested " + quantity
                    + " units, but only " + availableStock + " are available."
            );
        }

        availableStock -= quantity;

        System.out.println("Order successful!");
        System.out.println("Remaining stock: " + availableStock);
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
