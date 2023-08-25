package com.example.demo.service;

import java.util.List;


import org.springframework.data.domain.PageRequest;

import com.example.demo.model.Task;



public interface TaskService {

	public boolean addTask(Task task);
	public List<Task> getTask();
	public boolean updateTask(Long id  ,Task task);
	public  Object getAllTask(PageRequest pageRequest);
	public boolean deleteTask(Long id);
}
