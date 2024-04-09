package com.example.ToDo.controllers;

import com.example.ToDo.entity.Task;
import com.example.ToDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    //Create New Task
    @PostMapping("/createTask")
    public ResponseEntity<String> createTask(@RequestBody Task task){
        Task createdTask = taskService.createNewTask(task);
        if (createdTask != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("New Task Saved Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save task");
        }
    }

    //Read All Tasks
    @GetMapping("/allTasks")
    public ResponseEntity<List<Task>> allTasks(){
        return ResponseEntity.ok(taskService.showAll());
    }

    //Update Tasks with id
    @PutMapping("/updateTask/{id}")
    public ResponseEntity<String> updateTask(@PathVariable("id") Long id, @RequestBody Task updatedTask) {
        Task existingTask = taskService.findById(id);
        if (existingTask != null) {
            existingTask.setTask(updatedTask.getTask());
            existingTask.setCompleted(updatedTask.getCompleted());
            taskService.updateTask(existingTask);
            return ResponseEntity.status(HttpStatus.OK).body("Task with ID " + id + " updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " not found");
        }
    }

    //Delete task wth id
    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        Task taskToDelete = taskService.findById(id);
        if (taskToDelete != null) {
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Task with ID " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " not found");
        }
    }

}
