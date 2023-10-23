package com.tasks.controller;

import com.tasks.Exception.ResourceNotFoundException;
import com.tasks.model.Task;
import com.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "Tasks ",
        description = "CRUD Operations for Tasks"
)
@RestController
@RequestMapping("api/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved task",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Task.class))}),
       })
    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(){
        logger.info("Getting all tasks");
        List<Task> tasks = taskService.getAllTasks();
        logger.info("Retrieved all tasks");
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    @Operation(summary = "Add task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Task.class))}),
            @ApiResponse(responseCode = "404",description = "Task not found",content = @Content),
            @ApiResponse(responseCode = "400",description = "Empty/Null task name",content = @Content),
            @ApiResponse(responseCode = "400",description = "Task total price can not be <=0.0",content = @Content)})
    @PostMapping()
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task){
        logger.info("Adding task");
        return new ResponseEntity<Task>(taskService.addTask(task), HttpStatus.CREATED);
    }
    @Operation(summary = "Update existing task for an id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated task",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Task.class))}),
            @ApiResponse(responseCode = "404",description = "Task not found",content = @Content),
            @ApiResponse(responseCode = "400",description = "Empty/Null task name",content = @Content),
            @ApiResponse(responseCode = "400",description = "Task total price can not be <=0.0",content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id,@Valid @RequestBody Task task){
        logger.info("Updating task ");
        logger.debug("Updating task for id : "+id);
        Task optionalTask = taskService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + id));
        task.setId(id);
        return new ResponseEntity<Task>(taskService.addTask(task), HttpStatus.OK);
    }
    @Operation(summary = "Get existing task for an id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved task ",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Task.class))}),
            @ApiResponse(responseCode = "404",description = "Task not found for the id sent in",content = @Content)

           })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        logger.info("Retrieving task  ");
        logger.debug("Retrieving task for id : "+id);
        Task task = taskService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + id));
        return new ResponseEntity<Task>(task, HttpStatus.OK);

    }
    @Operation(summary = "Delete existing task for an id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved task ",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Task.class))}),
            @ApiResponse(responseCode = "404",description = "Task not found for the id sent in",content = @Content)

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable long id){
        logger.info("Deleting task");
        logger.debug("Deleting task for id : "+id);
        taskService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task does not exist with id :" + id));
        taskService.delete(id);
        logger.info("Deleted task ");
            return new ResponseEntity<>("Task successfully deleted!", HttpStatus.OK);
    }
}
