package app;

import java.io.*;
import java.util.*;

class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return key + " : " + value;
    }
}

class Box<T extends Number> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    double getDoubleValue() {
        return value.doubleValue();
    }
}

class Product implements Comparable<Product> {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    public String toString() {
        return name + " - Rs." + price;
    }
}

class Toolkit {
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    static double total(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }
}

class CrudRepository<T, ID> {
    protected Map<ID, T> data = new LinkedHashMap<>();

    public void save(ID id, T item) {
        data.put(id, item);
    }

    public T findById(ID id) {
        return data.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public void delete(ID id) {
        data.remove(id);
    }
}

class InMemoryRepository<T, ID> extends CrudRepository<T, ID> {
}

class FirstResource implements AutoCloseable {
    public void close() {
        System.out.println("Closing first resource");
    }
}

class SecondResource implements AutoCloseable {
    public void close() {
        System.out.println("Closing second resource");
    }
}

public class GenericToolkit {
    public static void main(String[] args) {

        Pair<Integer, String> pair = new Pair<>(1, "Java");
        System.out.println("Pair: " + pair);

        Box<Integer> box = new Box<>(25);
        System.out.println("Box value: " + box.getDoubleValue());

        System.out.println("Maximum: " + Toolkit.max(10, 30));

        List<Integer> numbers = Arrays.asList(10, 20, 30);
        System.out.println("Total: " + Toolkit.total(numbers));

        List<Number> output = new ArrayList<>();
        Toolkit.addNumbers(output);
        System.out.println("Added numbers: " + output);

        System.out.println("\nProducts from CSV:");

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/products.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read CSV: " + e.getMessage());
        }

        System.out.println("\nResource closing order:");

        try (FirstResource first = new FirstResource();
             SecondResource second = new SecondResource()) {
            System.out.println("Inside try block");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        InMemoryRepository<Product, Integer> repository =
                new InMemoryRepository<>();

        Product p1 = new Product("Keyboard", 1200);
        Product p2 = new Product("Mouse", 500);

        repository.save(1, p1);
        repository.save(2, p2);

        System.out.println("\nAll products:");
        for (Product p : repository.findAll()) {
            System.out.println(p);
        }

        System.out.println("Find ID 1: " + repository.findById(1));

        repository.delete(2);
        System.out.println("After delete: " + repository.findAll());
    }
}