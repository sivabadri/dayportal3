package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskServicelmpl implements TaskService{
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public boolean addTask(Task task) {
		// TODO Auto-generated method stub
		boolean taskExists = taskRepository.existsByEmail(task.getEmail());
		if(!taskExists) {
		taskRepository.save(task);
		return true;
		}
		else
		{
			return false;
		}
		
	}
	@Override
	public List<Task> getTask() {
		return taskRepository.findAll();
	}
	
	@Override
	public boolean updateTask(Long id,Task task) {
		
		//taskRepository.saveAndFlush(task);
		Optional<Task> existingTaskOptional =taskRepository.findById(id);
		if(existingTaskOptional.isPresent()) {
			
			Task taskExists=existingTaskOptional.get();
			taskExists.setName(task.getName());
			taskExists.setPassword(task.getPassword());
			taskExists.setEmail(task.getEmail());
			taskRepository.save(taskExists);
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public Object getAllTask(PageRequest pageRequest) {
		return pageRequest;
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean deleteTask(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
	