package com.example.jenpeupu.service;

import com.example.jenpeupu.model.Task;
import com.example.jenpeupu.repository.TaskRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        log.info("Task with id " + id + " not found");
        return null;
    }

    public Task saveTask(Task dataTask) {
        Task task = new Task(dataTask.getName(), dataTask.getDiscipline(), false, dataTask.getDate());

        Task savedTask = taskRepository.save(task);
        log.info("");
        log.info("Task with id " + savedTask.getId() + " saved, " + dataTask.getName() + " et " + dataTask.getDiscipline() + " ");
        log.info("");
        return savedTask;
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        Optional<Task> existingTask = taskRepository.findById(task.getId()); // à méditer
        task.setName(task.getName());
        task.setDiscipline(task.getDiscipline());
        task.setActive(task.getActive());
        task.setDate(task.getDate());

        Task savedTask = taskRepository.save(task);
        log.info("Task with id " + savedTask.getId() + " updated");
        return savedTask;
    }
}
