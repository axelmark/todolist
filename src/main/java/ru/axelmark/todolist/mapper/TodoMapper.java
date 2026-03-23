package ru.axelmark.todolist.mapper;

import org.springframework.stereotype.Component;
import ru.axelmark.todolist.dto.TodoRequest;
import ru.axelmark.todolist.dto.TodoResponse;
import ru.axelmark.todolist.entity.Todo;

@Component
public class TodoMapper {

    public Todo toEntity(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.isCompleted());
        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setId(todo.getId());
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setCompleted(todo.isCompleted());
        response.setCreatedAt(todo.getCreatedAt());
        response.setUpdatedAt(todo.getUpdatedAt());
        return response;
    }
}