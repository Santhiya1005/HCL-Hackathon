package app;

import java.util.*;

class Product {
    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void receiveStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        quantity += amount;
    }

    public void issueStock(int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > quantity) {
            throw new Exception("Insufficient stock");
        }
        quantity -= amount;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name
                + ", Quantity: " + quantity;
    }
}

class InventoryService {
    private Map<Integer, Product> products = new LinkedHashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product ID already exists");
        }
        products.put(product.getId(), product);
    }

    public Product findProduct(int id) {
        return products.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void receiveStock(int id, int amount) {
        Product product = findProduct(id);

        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        product.receiveStock(amount);
    }

    public void issueStock(int id, int amount) throws Exception {
        Product product = findProduct(id);

        if (product == null) {
            throw new NoSuchElementException("Product not found");
        }

        product.issueStock(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();

        // Sample data
        service.addProduct(new Product(1, "Wooden Plank", 50));
        service.addProduct(new Product(2, "Wooden Chair", 20));

        int choice;

        do {
            System.out.println("\n=== INVENTORY MANAGEMENT ===");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Receive Stock");
            System.out.println("5. Issue Stock");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter product ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter opening quantity: ");
                        int quantity = Integer.parseInt(sc.nextLine());

                        service.addProduct(new Product(id, name, quantity));
                        System.out.println("Product added successfully.");
                        break;

                    case 2:
                        System.out.println("\nProduct List:");
                        for (Product p : service.getAllProducts()) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        System.out.print("Enter product ID: ");
                        int searchId = Integer.parseInt(sc.nextLine());

                        Product found = service.findProduct(searchId);
                        if (found == null) {
                            System.out.println("Product not found.");
                        } else {
                            System.out.println(found);
                        }
                        break;

                    case 4:
                        System.out.print("Enter product ID: ");
                        int receiveId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter quantity to receive: ");
                        int receiveAmount = Integer.parseInt(sc.nextLine());

                        service.receiveStock(receiveId, receiveAmount);
                        System.out.println("Stock received successfully.");
                        break;

                    case 5:
                        System.out.print("Enter product ID: ");
                        int issueId = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter quantity to issue: ");
                        int issueAmount = Integer.parseInt(sc.nextLine());

                        service.issueStock(issueId, issueAmount);
                        System.out.println("Stock issued successfully.");
                        break;

                    case 0:
                        System.out.println("Exiting application.");
                        break;

                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = -1;
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                choice = -1;
            }

        } while (choice != 0);

        sc.close();
    }
}