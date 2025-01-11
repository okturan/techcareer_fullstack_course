package com.okanerturan.techcareer_java_bootcamp.controller;

import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoCreateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoUpdateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.response.TodoResponseDTO;
import com.okanerturan.techcareer_java_bootcamp.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Tag(name = "Todo API", description = "CRUD operations for Todo items")
public class TodoController {

    private final TodoService todoService;

    @Operation(summary = "Get all todos", description = "Retrieves a list of all todo items")
    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @Operation(summary = "Get todo by ID", description = "Retrieves a specific todo item by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> getTodoById(
            @PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @Operation(summary = "Create a new todo", description = "Creates a new todo item")
    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(
            @Valid @RequestBody TodoCreateRequestDTO request) {
        return new ResponseEntity<>(todoService.createTodo(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a todo", description = "Updates an existing todo item")
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequestDTO request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    @Operation(summary = "Delete a todo", description = "Deletes an existing todo item")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
