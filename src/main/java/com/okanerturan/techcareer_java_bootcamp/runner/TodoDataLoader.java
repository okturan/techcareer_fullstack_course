package com.okanerturan.techcareer_java_bootcamp.runner;

import com.okanerturan.techcareer_java_bootcamp.entity.Todo;
import com.okanerturan.techcareer_java_bootcamp.repository.TodoRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(1)
public class TodoDataLoader implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading sample todo data...");

        // Create sample todos
        Todo todo1 = Todo.builder()
                .title("Complete Spring Boot Assignment")
                .details("Implement CRUD operations and error handling in the Todo API")
                .completed(true)
                .build();

        Todo todo2 = Todo.builder()
                .title("Market alışverişi")
                .details("Süt\nYumurta\nEkmek")
                .completed(false).build();

        // Save todos
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        log.info("Sample todo data loaded successfully!");
    }
}
