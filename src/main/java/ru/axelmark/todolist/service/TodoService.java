package ru.axelmark.todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axelmark.todolist.dto.TodoRequest;
import ru.axelmark.todolist.dto.TodoResponse;
import ru.axelmark.todolist.entity.Todo;
import ru.axelmark.todolist.exception.ResourceNotFoundException;
import ru.axelmark.todolist.mapper.TodoMapper;
import ru.axelmark.todolist.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    // Конструктор для внедрения зависимостей
    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoResponse> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        return todoMapper.toResponse(todo);
    }

    @Transactional
    public TodoResponse createTodo(TodoRequest request) {
        Todo todo = todoMapper.toEntity(request);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public TodoResponse updateTodo(Long id, TodoRequest request) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        existingTodo.setTitle(request.getTitle());
        existingTodo.setDescription(request.getDescription());
        existingTodo.setCompleted(request.isCompleted());

        Todo updatedTodo = todoRepository.save(existingTodo);
        return todoMapper.toResponse(updatedTodo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}