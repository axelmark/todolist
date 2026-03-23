package ru.axelmark.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.axelmark.todolist.dto.TodoRequest;
import ru.axelmark.todolist.dto.TodoResponse;
import ru.axelmark.todolist.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    // Явный конструктор для внедрения зависимости
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        List<TodoResponse> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        TodoResponse todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest request) {
        TodoResponse createdTodo = todoService.createTodo(request);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        TodoResponse updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}