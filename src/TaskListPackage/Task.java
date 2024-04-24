package TaskListPackage;

import java.time.LocalDate;

// Task class representing a task in the to-do list
public class Task {
    private String title; // Title of the task
    private LocalDate dueDate; // Due date of the task
    private boolean completed; // Flag indicating if the task is completed
    private Priority priority; // Priority level of the task
    private Occurence occurence; // Frequency of the task
    private Tag tag; // Tag associated with the task

    // Constructor to initialize a task with given parameters
    public Task(String title, LocalDate date, Priority priority, Occurence occurence, Tag tag) {
        this.title = title;
        this.dueDate = date;
        this.completed = false; // By default, the task is not completed
        this.priority = priority;
        this.occurence = occurence;
        this.tag = tag;
    }

    // Getter and setter methods for title, date, completed, priority, occurence, and tag
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return dueDate;
    }
    
    public void setDate(LocalDate date) {
        this.dueDate = date;
    }

    public boolean isTaskCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public Occurence getOccurence() {
        return occurence;
    }

    public void setOccurence(Occurence occurence) {
        this.occurence = occurence;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    // Override toString() method to return a string representation of the task
    @Override
    public String toString() {
        // Construct a string with task details including title, due date, priority, frequency, and status
        String status = completed ? "Completed" : "Pending"; // Determine the status of the task
        return "Title: " + title + " Due Date: " + dueDate + " Priority: " + priority +" Tag: " + tag + " Frequency: " + occurence + " Status: " + status;
    }
}

// Enum representing the priority levels of a task
enum Priority {
    HIGH, // High priority
    MEDIUM, // Medium priority
    LOW, // Low priority
}

// Enum representing the frequency of a task
enum Occurence {
    UNIQUE, // Task occurs only once
    DAILY, // Task occurs daily
    WEEKLY, // Task occurs weekly
    YEARLY, // Task occurs yearly
}

// Enum representing the tags associated with a task
enum Tag {
    STUDY, // Tag for study-related tasks
    WORK, // Tag for work-related tasks
    HOBBY, // Tag for hobby-related tasks
    MISCELLANEOUS, // Tag for miscellaneous tasks
}