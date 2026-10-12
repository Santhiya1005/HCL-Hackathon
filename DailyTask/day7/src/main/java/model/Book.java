package model;

public class Book {
    private final int id;
    private final String title;
    private final boolean overdue;

    public Book(int id, String title, boolean overdue) {
        this.id = id;
        this.title = title;
        this.overdue = overdue;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOverdue() {
        return overdue;
    }

    @Override
    public String toString() {
        return id + " - " + title + (overdue ? " (OVERDUE)" : "");
    }
}
