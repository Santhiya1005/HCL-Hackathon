package app;

import model.Book;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryWaitlist {

    // List: keeps catalogue order and supports indexed access.
    private final List<Book> catalogue = new ArrayList<>();

    // LinkedHashSet: removes duplicate titles while preserving insertion order.
    private final LinkedHashSet<String> registeredTitles = new LinkedHashSet<>();

    // Queue: normal requests are processed in FIFO order.
    private final ArrayDeque<String> normalWaitlist = new ArrayDeque<>();

    // PriorityQueue: faculty requests are processed before regular requests.
    // Within each priority, lower request number is served first.
    private final PriorityQueue<Request> priorityWaitlist =
            new PriorityQueue<>(Comparator
                    .comparingInt(Request::getPriority)
                    .thenComparingInt(Request::getOrder));

    private int nextRequestOrder = 1;

    private static class Request {
        private final String name;
        private final int priority;
        private final int order;

        Request(String name, int priority, int order) {
            this.name = name;
            this.priority = priority;
            this.order = order;
        }

        int getPriority() {
            return priority;
        }

        int getOrder() {
            return order;
        }

        @Override
        public String toString() {
            return name + (priority == 1 ? " [FACULTY]" : " [REGULAR]");
        }
    }

    public LibraryWaitlist() {
        catalogue.add(new Book(1, "Clean Code", false));
        catalogue.add(new Book(2, "Effective Java", false));
        catalogue.add(new Book(3, "Java Fundamentals", true));
        catalogue.add(new Book(4, "Data Structures", false));

        registeredTitles.add("Clean Code");
        registeredTitles.add("Effective Java");
        registeredTitles.add("Clean Code"); // Duplicate ignored.
    }

    private void showCatalogue() {
        System.out.println("\n--- Library Catalogue ---");
        for (Book book : catalogue) {
            System.out.println(book);
        }
    }

    private void registerTitle(Scanner scanner) {
        System.out.print("Enter title to register: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }

        if (registeredTitles.add(title)) {
            System.out.println("Title registered.");
        } else {
            System.out.println("Duplicate title ignored.");
        }
    }

    private void addWaitlistRequest(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Request type (1 = Faculty, 2 = Regular): ");
        String input = scanner.nextLine();

        if (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid request type.");
            return;
        }

        int priority = input.equals("1") ? 1 : 2;
        priorityWaitlist.add(new Request(name, priority, nextRequestOrder++));

        System.out.println("Request added.");
    }

    private void addNormalQueue(Scanner scanner) {
        System.out.print("Enter name for normal FIFO queue: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        normalWaitlist.offer(name);
        System.out.println("Added to normal waitlist.");
    }

    private void serveRequest() {
        Request request = priorityWaitlist.poll();

        if (request == null) {
            System.out.println("Priority waitlist is empty.");
        } else {
            System.out.println("Serving: " + request);
        }
    }

    private void serveNormalRequest() {
        String name = normalWaitlist.poll();

        if (name == null) {
            System.out.println("Normal waitlist is empty.");
        } else {
            System.out.println("Serving: " + name);
        }
    }

    private void removeOverdueBooks() {
        // Iterator.remove safely removes items while iterating.
        Iterator<Book> iterator = catalogue.iterator();
        int removed = 0;

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (book.isOverdue()) {
                iterator.remove();
                removed++;
            }
        }

        System.out.println("Overdue catalogue entries removed: " + removed);
    }

    private void showCollections() {
        System.out.println("\nRegistered titles: " + registeredTitles);
        System.out.println("Normal FIFO waitlist: " + normalWaitlist);
        System.out.println("Priority waitlist: " + priorityWaitlist);
    }

    private void benchmarkCollections() {
        final int size = 20000;
        final int insertCount = 2000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long start = System.nanoTime();
        for (int i = 0; i < insertCount; i++) {
            arrayList.add(0, -i);
        }
        long arrayInsertTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < insertCount; i++) {
            linkedList.add(0, -i);
        }
        long linkedInsertTime = System.nanoTime() - start;

        start = System.nanoTime();
        long checksum = 0;
        for (int i = 0; i < 5000; i++) {
            checksum += arrayList.get(i);
        }
        long arrayGetTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            checksum += linkedList.get(i);
        }
        long linkedGetTime = System.nanoTime() - start;

        System.out.println("\n--- Timing Results (nanoseconds) ---");
        System.out.printf("%-20s %15s %15s%n",
                "Operation", "ArrayList", "LinkedList");
        System.out.printf("%-20s %15d %15d%n",
                "Insert at index 0", arrayInsertTime, linkedInsertTime);
        System.out.printf("%-20s %15d %15d%n",
                "get(i), 5000 reads", arrayGetTime, linkedGetTime);
        System.out.println("Checksum: " + checksum);
        System.out.println("Times vary by machine and JVM run.");
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n===== LIBRARY WAITLIST =====");
                System.out.println("1. Show catalogue");
                System.out.println("2. Register a unique title");
                System.out.println("3. Add priority waitlist request");
                System.out.println("4. Serve priority request");
                System.out.println("5. Add normal FIFO request");
                System.out.println("6. Serve normal FIFO request");
                System.out.println("7. Remove overdue catalogue entries");
                System.out.println("8. Show collections");
                System.out.println("9. Benchmark ArrayList vs LinkedList");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1": showCatalogue(); break;
                    case "2": registerTitle(scanner); break;
                    case "3": addWaitlistRequest(scanner); break;
                    case "4": serveRequest(); break;
                    case "5": addNormalQueue(scanner); break;
                    case "6": serveNormalRequest(); break;
                    case "7": removeOverdueBooks(); break;
                    case "8": showCollections(); break;
                    case "9": benchmarkCollections(); break;
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }
}
