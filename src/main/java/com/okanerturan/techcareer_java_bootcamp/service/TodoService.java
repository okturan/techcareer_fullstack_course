package com.okanerturan.techcareer_java_bootcamp.service;

import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoCreateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoUpdateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.response.TodoResponseDTO;
import com.okanerturan.techcareer_java_bootcamp.entity.Todo;
import com.okanerturan.techcareer_java_bootcamp.exception.DuplicateTodoException;
import com.okanerturan.techcareer_java_bootcamp.mapper.TodoMapper;
import com.okanerturan.techcareer_java_bootcamp.repository.TodoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Transactional(readOnly = true)
    public List<TodoResponseDTO> getAllTodos() {
        return todoRepository.findAll().stream().map(todoMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TodoResponseDTO getTodoById(Long id) {
        return todoRepository.findById(id).map(todoMapper::toResponse).orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
    }

    @Transactional
    public TodoResponseDTO createTodo(TodoCreateRequestDTO request) {
        if (todoRepository.existsByTitleAndDetails(request.getTitle(), request.getDetails())) {
            throw new DuplicateTodoException();
        }
        Todo todo = todoMapper.toEntity(request);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public TodoResponseDTO updateTodo(Long id, TodoUpdateRequestDTO request) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));

        // Check if another todo (excluding current one) exists with same title and details
        boolean todoWithSameDetailsExists = todoRepository.existsByTitleAndDetailsAndIdNot(request.getTitle(), request.getDetails(), id);

        if (todoWithSameDetailsExists) {
            throw new DuplicateTodoException();
        }

        Todo updatedTodo = todoMapper.updateEntityFromRequest(todo, request);
        Todo savedTodo = todoRepository.save(updatedTodo);
        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo not found with id: " + id);
        }
        todoRepository.deleteById(id);
    }
}
