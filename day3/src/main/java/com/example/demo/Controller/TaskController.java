package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("getTask")
	public ResponseEntity<List<Task>> getTask(){
		return ResponseEntity.status(200).body(taskService.getTask());
	}
	
	@GetMapping("/getAllTask")
	public ResponseEntity<Object> getAllTask(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder){
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));
		return ResponseEntity.status(200).body(taskService.getAllTask(pageRequest));
				}

	@PostMapping("/addTask")
	public ResponseEntity<String> addtask(@RequestBody Task task){
		boolean dataSaved = taskService.addTask(task);
		if(dataSaved) {
			return ResponseEntity.status(200).body("Task added successfully!");
			}
		else
		{
			return ResponseEntity.status(404).body("Something went wrong!");
		}
	}
	public ResponseEntity<String> updateTask(@PathVariable Long id,@RequestBody Task task){
		boolean taskData = taskService.updateTask(id,task);
		if(taskData) {
			return ResponseEntity.status(200).body("User updated successfully");
		}
		else {
			return ResponseEntity.status(404).body("No record found");
		}
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id){
		boolean userDeleted = taskService.deleteTask(id);
		if(userDeleted) {
			return ResponseEntity.status(200).body("User deleted successfully");
		} else {
			return ResponseEntity.status(404).body("No record found to be updated");
		}
	}
	
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam Long id){
		boolean userDeleted = taskService.deleteTask(id);
		if(userDeleted) {
			return ResponseEntity.status(200).body("User deleted successfully");
		} else {
			return ResponseEntity.status(404).body("No record found to be updated");
		}
	}
}
