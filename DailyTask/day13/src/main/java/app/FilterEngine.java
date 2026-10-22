package app;

import java.util.*;
import java.util.function.*;

class Product {
    private String name;
    private String category;
    private double price;
    private int stock;

    Product(String name, String category, double price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String toString() {
        return name + " | " + category + " | Rs." + price
                + " | Stock: " + stock;
    }
}

@FunctionalInterface
interface ProductCheck {
    boolean test(Product product);
}

public class FilterEngine {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Keyboard", "Electronics", 1200, 10),
            new Product("Mouse", "Electronics", 500, 0),
            new Product("Chair", "Furniture", 2500, 5),
            new Product("Desk", "Furniture", 5000, 3),
            new Product("USB Cable", "Electronics", 250, 20)
        );

        // 1. Predicate: conditions for filtering products
        Predicate<Product> priceCheck = p -> p.getPrice() <= 3000;
        Predicate<Product> categoryCheck =
                p -> p.getCategory().equals("Electronics");
        Predicate<Product> stockCheck = p -> p.getStock() > 0;

        Predicate<Product> finalCheck =
                priceCheck.and(categoryCheck).and(stockCheck);

        System.out.println("FILTERED PRODUCTS");
        products.stream()
                .filter(finalCheck)
                .forEach(System.out::println);

        // Predicate or() and negate()
        System.out.println("\nPRICE ABOVE 3000 OR OUT OF STOCK");
        products.stream()
                .filter(p -> p.getPrice() > 3000 || p.getStock() == 0)
                .forEach(System.out::println);

        System.out.println("\nIN-STOCK PRODUCTS");
        products.stream()
                .filter(stockCheck.negate().negate())
                .forEach(System.out::println);

        // 2. Function: transform product into a display string
        Function<Product, String> getName = Product::getName;
        Function<String, String> makeUpper = String::toUpperCase;
        Function<Product, String> displayName = getName.andThen(makeUpper);

        System.out.println("\nPRODUCT NAMES");
        products.stream()
                .map(displayName)
                .forEach(System.out::println);

        // Function compose()
        Function<Integer, Integer> multiplyByTwo = n -> n * 2;
        Function<Integer, Integer> addFive = n -> n + 5;

        System.out.println("\nCOMPOSE RESULT: "
                + multiplyByTwo.compose(addFive).apply(10));

        // 3. Consumer: print each product
        Consumer<Product> printProduct = System.out::println;

        System.out.println("\nALL PRODUCTS");
        products.forEach(printProduct);

        // 4. Supplier: provide a default product
        Supplier<Product> defaultProduct =
                () -> new Product("Default Item", "General", 100, 1);

        System.out.println("\nDEFAULT PRODUCT");
        System.out.println(defaultProduct.get());

        // 5. Custom functional interface
        ProductCheck affordable = p -> p.getPrice() < 1000;

        System.out.println("\nAFFORDABLE PRODUCTS");
        for (Product p : products) {
            if (affordable.test(p)) {
                System.out.println(p);
            }
        }

        // 6. Method reference: instance method reference
        System.out.println("\nSORTED BY PRICE");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(System.out::println);

        // 7. Effectively final variable capture
        double limit = 2000;
        Predicate<Product> belowLimit = p -> p.getPrice() < limit;

        System.out.println("\nBELOW PRICE LIMIT");
        products.stream()
                .filter(belowLimit)
                .forEach(System.out::println);
    }
}