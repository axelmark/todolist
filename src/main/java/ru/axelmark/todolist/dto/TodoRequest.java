package ru.axelmark.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    private boolean completed;

    // Геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}