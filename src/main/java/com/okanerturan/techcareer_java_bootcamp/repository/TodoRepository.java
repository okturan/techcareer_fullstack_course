package com.okanerturan.techcareer_java_bootcamp.repository;

import com.okanerturan.techcareer_java_bootcamp.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    boolean existsByTitleAndDescription(String title, String description);

    boolean existsByTitleAndDescriptionAndIdNot(String title, String description, Long id);

}
