package com.tasks.service;

import com.tasks.repository.TaskRepository;
import com.tasks.model.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService
{
    @Autowired
    TaskRepository taskRepository;

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public List<Task> getAllTasks(){
        logger.info("Getting all Tasks");
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public Task addTask(Task task){
        logger.info("Adding task");
        taskRepository.save(task);
        return task;
    }

    public Optional<Task> findById(Long id){
        logger.info("Finding Task");
        logger.debug("Finding Task for id :" +id);
        return taskRepository.findById(id);

    }
    public void delete(Long id){
        logger.info("Deleting Task");
        logger.debug("Deleting Task for id :" +id);
        taskRepository.deleteById(id);

    }
}
