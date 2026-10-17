package app;

import java.util.*;

public class MemoryLab {

    // Experiment 1: StackOverflowError
    static void stackTest() {
        stackTest();
    }

    // Experiment 2: OutOfMemoryError
    static void memoryTest() {
        List<int[]> data = new ArrayList<>();

        while (true) {
            data.add(new int[100_000]);
        }
    }

    // Experiment 3: String concatenation vs StringBuilder
    static void stringTest() {
        int count = 20_000;

        long start = System.nanoTime();
        String text = "";

        for (int i = 0; i < count; i++) {
            text += "a";
        }

        long concatTime = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            builder.append("a");
        }

        long builderTime = System.nanoTime() - start;

        System.out.println("String += time: " + concatTime / 1_000_000 + " ms");
        System.out.println("StringBuilder time: " + builderTime / 1_000_000 + " ms");
        System.out.println("Same length: " + (text.length() == builder.length()));
    }

    // Experiment 4: Garbage collection
    static void gcTest() {
        for (int i = 0; i < 100_000; i++) {
            byte[] data = new byte[1024];
        }

        System.gc();
        System.out.println("GC experiment completed. Check GC logs.");
    }

    // Experiment 5: Integer cache
    static void integerTest() {
        Integer a = 100;
        Integer b = 100;

        Integer x = 1000;
        Integer y = 1000;

        System.out.println("100 using == : " + (a == b));
        System.out.println("1000 using == : " + (x == y));

        System.out.println("1000 using equals(): " + x.equals(y));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("DAY 10 - MEMORY LAB");
        System.out.println("1. StackOverflowError");
        System.out.println("2. OutOfMemoryError");
        System.out.println("3. String vs StringBuilder");
        System.out.println("4. Garbage Collection");
        System.out.println("5. Integer Cache");
        System.out.println("0. Exit");
        System.out.print("Choose experiment: ");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                stackTest();
                break;
            case 2:
                memoryTest();
                break;
            case 3:
                stringTest();
                break;
            case 4:
                gcTest();
                break;
            case 5:
                integerTest();
                break;
            case 0:
                System.out.println("Exited.");
                break;
            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}