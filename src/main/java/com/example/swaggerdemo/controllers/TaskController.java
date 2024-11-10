package com.example.swaggerdemo.controllers;

import com.example.swaggerdemo.entities.Task;
import com.example.swaggerdemo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PostMapping("/bulk")
    public String createTasksInBulk(@RequestBody List<Task> tasks) {
        taskRepository.saveAll(tasks);
        return "Tasks created successfully!";
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setCompleted(taskDetails.isCompleted());

            return taskRepository.saveAndFlush(task);
        }

        return null;
    }

    @DeleteMapping
    public String deleteAllTasks() {
        taskRepository.deleteAll();
        return "All tasks successfully deleted!";
    }

    @DeleteMapping("/{id}")
    public String deleteTaskById(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Task successfully deleted!";
    }
}
