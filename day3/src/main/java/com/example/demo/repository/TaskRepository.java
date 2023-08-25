package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Task;


public interface TaskRepository extends JpaRepository<Task, Integer> {

	boolean existsByEmail(String email);
	Optional<Task> findById(Long id);
	

}
