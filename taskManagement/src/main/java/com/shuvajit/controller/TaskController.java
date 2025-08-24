package com.shuvajit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shuvajit.pojo.Task;

@RestController
@RequestMapping("/api") 
public class TaskController {

    private final List<Task> taskList = new ArrayList<>();
    private int currentId = 1;

    public TaskController() {
        taskList.add(new Task(currentId++, "Learn Spring Boot", "Complete basic CRUD REST API"));
        taskList.add(new Task(currentId++, "Read Documentation", "Go through Spring official docs"));
        taskList.add(new Task(currentId++, "Practice Postman", "Test all API endpoints with JSON"));
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setId(currentId++);
        taskList.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return taskList.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        boolean removed = taskList.removeIf(task -> task.getId() == id);
        return removed
                ? new ResponseEntity<>("Task deleted successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Task not found!", HttpStatus.NOT_FOUND);
    }
}
