package ru.axelmark.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axelmark.todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}