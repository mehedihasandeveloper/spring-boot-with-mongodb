package com.mehedi.mongo_db_practice_01.controller;

import com.mehedi.mongo_db_practice_01.model.Tasks;
import com.mehedi.mongo_db_practice_01.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks createTask(@RequestBody Tasks task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Tasks getTask(@PathVariable String taskId) {
        return taskService.getTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Tasks> findTaskUsingSeverity(@PathVariable int severity) {
        return taskService.getBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Tasks> findTaskUsingAssignee(@PathVariable String assignee) {
        return taskService.getByAssignee(assignee);
    }

    @PutMapping
    public Tasks modifyTask(@RequestBody Tasks task){
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String removeTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId);
    }
}
