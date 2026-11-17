package com.college.eventmgmt.model;

/**
 * Represents an Event Category (e.g., Symposium, Workshop, Seminar, Cultural Fest).
 * Corresponds to Course Code: 25ITT32 - Java Programming
 */
public class Category {
    private String categoryId;
    private String categoryName;
    private String description;

    public Category(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", categoryId, categoryName, description);
    }
}
