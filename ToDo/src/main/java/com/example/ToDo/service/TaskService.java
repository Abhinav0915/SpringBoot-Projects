package com.example.ToDo.service;

import com.example.ToDo.entity.Task;
import com.example.ToDo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public Task createNewTask(Task task){
        return taskRepo.save(task);
    }

    public List<Task> showAll(){
        return taskRepo.findAll();
    }

    public Task findById(Long id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        return optionalTask.orElse(null);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public void updateTask(Task task){
        taskRepo.save(task);
    }



}
