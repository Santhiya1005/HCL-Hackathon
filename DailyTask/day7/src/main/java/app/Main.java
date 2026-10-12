package app;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ArrayList stores the book catalogue
        ArrayList<String> books = new ArrayList<>();
        books.add("Java");
        books.add("Python");
        books.add("C++");

        // LinkedHashSet prevents duplicate student names
        LinkedHashSet<String> students = new LinkedHashSet<>();

        // ArrayDeque stores normal waitlist requests
        ArrayDeque<String> waitlist = new ArrayDeque<>();

        // PriorityQueue serves faculty before students
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        int choice;

        do {
            System.out.println("\n--- Library Waitlist ---");
            System.out.println("1. View books");
            System.out.println("2. Add student");
            System.out.println("3. Add waitlist request");
            System.out.println("4. Add priority request");
            System.out.println("5. Serve normal request");
            System.out.println("6. View all collections");
            System.out.println("7. Remove overdue book");
            System.out.println("8. Compare ArrayList and LinkedList");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.println("Books: " + books);
                    break;

                case 2:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    if (students.add(name)) {
                        System.out.println("Student added.");
                    } else {
                        System.out.println("Student already exists.");
                    }
                    break;

                case 3:
                    System.out.print("Enter name: ");
                    waitlist.offer(sc.nextLine());
                    System.out.println("Added to waitlist.");
                    break;

                case 4:
                    System.out.print("Enter priority name (A-Z): ");
                    priorityQueue.offer(sc.nextLine());
                    System.out.println("Added to priority queue.");
                    break;

                case 5:
                    if (waitlist.isEmpty()) {
                        System.out.println("Waitlist is empty.");
                    } else {
                        System.out.println("Serving: " + waitlist.poll());
                    }
                    break;

                case 6:
                    System.out.println("Books: " + books);
                    System.out.println("Students: " + students);
                    System.out.println("Waitlist: " + waitlist);
                    System.out.println("Priority queue: " + priorityQueue);
                    break;

                case 7:
                    Iterator<String> it = books.iterator();

                    while (it.hasNext()) {
                        String book = it.next();

                        if (book.equalsIgnoreCase("C++")) {
                            it.remove();
                            System.out.println("Overdue book removed: " + book);
                        }
                    }
                    break;

                case 8:
                    ArrayList<Integer> a = new ArrayList<>();
                    LinkedList<Integer> l = new LinkedList<>();

                    for (int i = 0; i < 10000; i++) {
                        a.add(i);
                        l.add(i);
                    }

                    long start = System.nanoTime();
                    a.add(0, -1);
                    System.out.println("ArrayList insert time: "
                            + (System.nanoTime() - start) + " ns");

                    start = System.nanoTime();
                    l.add(0, -1);
                    System.out.println("LinkedList insert time: "
                            + (System.nanoTime() - start) + " ns");

                    start = System.nanoTime();
                    a.get(5000);
                    System.out.println("ArrayList get time: "
                            + (System.nanoTime() - start) + " ns");

                    start = System.nanoTime();
                    l.get(5000);
                    System.out.println("LinkedList get time: "
                            + (System.nanoTime() - start) + " ns");
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}