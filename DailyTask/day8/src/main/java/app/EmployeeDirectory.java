package app;

import java.util.*;

class Employee implements Comparable<Employee> {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;
        return id == other.id;
    }

    public int hashCode() {
        return Integer.hashCode(id);
    }

    public String toString() {
        return id + " " + name + " | " + department + " | Rs." + salary;
    }
}

class BadEmployee {
    int id;

    BadEmployee(int id) {
        this.id = id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BadEmployee)) return false;
        BadEmployee other = (BadEmployee) obj;
        return id == other.id;
    }
}

public class EmployeeDirectory {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new Employee(103, "Ravi", "IT", 50000));
        employees.add(new Employee(101, "Anu", "HR", 60000));
        employees.add(new Employee(104, "Kumar", "IT", 75000));
        employees.add(new Employee(102, "Priya", "HR", 75000));
        employees.add(new Employee(105, "Divya", "Sales", 45000));
        employees.add(new Employee(106, "Arun", "IT", 65000));

        HashMap<Integer, Employee> employeeMap = new HashMap<>();

        for (Employee e : employees) {
            employeeMap.put(e.id, e);
        }

        System.out.println("EMPLOYEE LOOKUP");
        System.out.println(employeeMap.get(103));

        System.out.println("\nMissing employee:");
        System.out.println(employeeMap.get(999)); // null

        HashMap<String, Integer> departmentCount = new HashMap<>();

        for (Employee e : employees) {
            departmentCount.merge(e.department, 1, Integer::sum);
        }

        TreeMap<String, Integer> sortedReport = new TreeMap<>(departmentCount);

        System.out.println("\nDEPARTMENT REPORT");
        System.out.println(sortedReport);

        Collections.sort(employees);

        System.out.println("\nSORTED BY ID");
        for (Employee e : employees) {
            System.out.println(e);
        }

        employees.sort(
            Comparator.comparingDouble((Employee e) -> e.salary)
                      .reversed()
                      .thenComparing(e -> e.name)
        );

        System.out.println("\nTOP 5 SALARIES");
        for (int i = 0; i < 5 && i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }

        HashSet<BadEmployee> badSet = new HashSet<>();
        badSet.add(new BadEmployee(1));
        badSet.add(new BadEmployee(1));

        System.out.println("\nWITHOUT hashCode()");
        System.out.println("Set size: " + badSet.size());

        HashSet<Employee> goodSet = new HashSet<>();
        goodSet.add(new Employee(1, "Anu", "HR", 60000));
        goodSet.add(new Employee(1, "Anu", "HR", 60000));

        System.out.println("\nWITH equals() AND hashCode()");
        System.out.println("Set size: " + goodSet.size());
    }
}