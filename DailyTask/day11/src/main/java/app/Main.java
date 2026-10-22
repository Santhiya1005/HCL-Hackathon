package app;

import java.util.*;

class Student {
    private int id;
    private String name;
    private List<String> subjects;

    public Student(int id, String name, List<String> subjects) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.subjects = new ArrayList<>(
                Objects.requireNonNull(subjects, "Subjects cannot be null"));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public void addSubject(String subject) {
        subjects.add(Objects.requireNonNull(subject, "Subject cannot be null"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + subjects;
    }
}

class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        Objects.requireNonNull(student, "Student cannot be null");

        if (students.contains(student)) {
            throw new IllegalArgumentException("Student ID already exists");
        }

        students.add(student);
    }

    public Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}

interface Repository<T, ID> {
    void save(ID id, T item);
    T findById(ID id);
    List<T> findAll();
}

class StudentRepository implements Repository<Student, Integer> {
    private final Map<Integer, Student> students = new LinkedHashMap<>();

    public void save(Integer id, Student student) {
        students.put(id, Objects.requireNonNull(student));
    }

    public Student findById(Integer id) {
        return students.get(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }
}

class Course {
    private int id;
    private String name;

    Course(int id, String name) {
        this.id = id;
        this.name = Objects.requireNonNull(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return id + " - " + name;
    }
}

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        Student s1 = new Student(
                101, "Anu", Arrays.asList("Java", "SQL"));
        Student s2 = new Student(
                102, "Ravi", Arrays.asList("Python", "AI"));

        service.addStudent(s1);
        service.addStudent(s2);

        System.out.println("ALL STUDENTS");
        for (Student student : service.getAllStudents()) {
            System.out.println(student);
        }

        System.out.println("\nSEARCH STUDENT");
        System.out.println(service.findStudent(101));

        System.out.println("\nDUPLICATE STUDENT TEST");
        try {
            service.addStudent(
                    new Student(101, "Another Name", Arrays.asList("C")));
        } catch (IllegalArgumentException e) {
            System.out.println("Error handled: " + e.getMessage());
        }

        System.out.println("\nREPOSITORY TEST");
        StudentRepository repository = new StudentRepository();
        repository.save(s1.getId(), s1);
        repository.save(s2.getId(), s2);
        System.out.println(repository.findById(102));
        System.out.println("Repository count: " + repository.findAll().size());

        System.out.println("\nSECOND ENTITY");
        Course course = new Course(1, "Core Java");
        System.out.println(course);
    }
}