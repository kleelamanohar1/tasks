package com.tasks.controller;

import com.MainApplication;
import com.tasks.model.Task;
import com.tasks.service.TaskService;
import com.tasks.utils.TaskUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;


@WebMvcTest(TaskController.class)
@ContextConfiguration(classes = MainApplication.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;




    @Test
    void shouldReturnTask() throws Exception {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task task = new Task("Process Events","Processes Daily Events",false,createdDate,updatedDate);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/tasks")
                        .content(TaskUtils.asJsonString(task))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    @Test
    void shouldReturnAll() throws Exception {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        Task vizioTask = new Task("Process Vizio Events","Processes Daily Events for vizio",false,createdDate,updatedDate);
        Task ctvTask = new Task("Process CTV Events","Processes Daily Events for ctv",false,createdDate,updatedDate);

        List<Task> tasks = new ArrayList<>(
                Arrays.asList(samsungTask,
                        vizioTask,
                        ctvTask));
        when(taskService.getAllTasks()).thenReturn(tasks);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/tasks"))
                        .andExpect(status().isOk())
                         .andExpect(jsonPath("$.size()").value(tasks.size()));


    }
    @Test
    void shouldReturnTask404() throws Exception {

        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        Task vizioTask = new Task("Process Vizio Events","Processes Daily Events for vizio",false,createdDate,updatedDate);
        Task ctvTask = new Task("Process CTV Events","Processes Daily Events for ctv",false,createdDate,updatedDate);

        List<Task> tasks = new ArrayList<>(
                Arrays.asList(samsungTask,
                        vizioTask,
                        ctvTask));
        when(taskService.getAllTasks()).thenReturn(tasks);
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/tasks/4"))
                .andExpect(status().isNotFound());



    }
    @Test
    void shouldReturnTask404Update() throws Exception {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        when(taskService.findById(4L)).thenReturn(Optional.empty());
        when(taskService.addTask(any(Task.class))).thenReturn(samsungTask);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/4").contentType(MediaType.APPLICATION_JSON)
                        .content(TaskUtils.asJsonString(samsungTask)))
                .andExpect(status().isNotFound());




    }
    @Test
    void shouldReturnTask404Delete() throws Exception {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
       Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        when(taskService.findById(4L)).thenReturn(Optional.empty());
        when(taskService.addTask(any(Task.class))).thenReturn(samsungTask);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/4").contentType(MediaType.APPLICATION_JSON)
                        .content(TaskUtils.asJsonString(samsungTask)))
                .andExpect(status().isNotFound());




    }
    @Test
    void shouldUpdateTask() throws Exception {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        Task updatedTask = new Task("Process Vizio Events","Processes Daily Events for vizio",false,createdDate,updatedDate);
        when(taskService.findById(4L)).thenReturn(Optional.of(samsungTask));
        when(taskService.addTask(any(Task.class))).thenReturn(updatedTask);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/tasks/4").contentType(MediaType.APPLICATION_JSON)
                        .content(TaskUtils.asJsonString(updatedTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(updatedTask.isCompleted()));






    }
    @Test
    void shouldDeleteTask() throws Exception {

        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Processes Daily Events for samsung",false,createdDate,updatedDate);
        when(taskService.findById(1L)).thenReturn(Optional.of(samsungTask));
        doNothing().when(taskService).delete(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
