package app;

import exception.InsufficientStockException;
import exception.InvalidQuantityException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderProcessor processor = new OrderProcessor();

        while (true) {
            System.out.println("\n===== ORDER PROCESSOR =====");
            System.out.println("1. Place order");
            System.out.println("2. View stock");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());

                        try {
                            processor.processOrder(quantity);
                        } catch (InsufficientStockException
                                 | InvalidQuantityException e) {
                            System.out.println("Order failed: " + e.getMessage());
                        } finally {
                            System.out.println("Order attempt audited.");
                        }
                        break;

                    case 2:
                        System.out.println(
                                "Available stock: " + processor.getAvailableStock()
                        );
                        break;

                    case 3:
                        System.out.println("Exiting Order Processor.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid menu choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            } finally {
                System.out.println("Menu operation completed.");
            }
        }
    }
}
