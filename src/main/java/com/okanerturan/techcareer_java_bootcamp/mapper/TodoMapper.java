package com.okanerturan.techcareer_java_bootcamp.mapper;

import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoCreateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.request.TodoUpdateRequestDTO;
import com.okanerturan.techcareer_java_bootcamp.dto.response.TodoResponseDTO;
import com.okanerturan.techcareer_java_bootcamp.entity.Todo;

import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoCreateRequestDTO request) {
        return Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .build();
    }

    public Todo updateEntityFromRequest(Todo todo, TodoUpdateRequestDTO request) {
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setCompleted(request.getCompleted());
        return todo;
    }

    public TodoResponseDTO toResponse(Todo todo) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .build();
    }
}
