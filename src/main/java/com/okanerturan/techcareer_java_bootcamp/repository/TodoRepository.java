package com.okanerturan.techcareer_java_bootcamp.repository;

import com.okanerturan.techcareer_java_bootcamp.entity.Todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    boolean existsByTitleAndDetails(String title, String details);

    boolean existsByTitleAndDetailsAndIdNot(String title, String details, Long id);

}
