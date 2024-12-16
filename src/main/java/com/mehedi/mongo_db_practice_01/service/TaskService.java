package com.mehedi.mongo_db_practice_01.service;

import com.mehedi.mongo_db_practice_01.model.Tasks;
import com.mehedi.mongo_db_practice_01.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Tasks addTask(Tasks task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Tasks> findAllTasks() {
        return taskRepository.findAll();
    }

    public Tasks getTaskByTaskId(String taskId) {
        return taskRepository.findById(taskId).get();
    }

    public List<Tasks> getBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Tasks> getByAssignee(String assignee) {
        return taskRepository.getTasksByAssignee(assignee);
    }

    public Tasks updateTask(Tasks taskRequest) {
        Tasks existingTask = taskRepository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return taskId + " deleted successfully!";
    }
}
