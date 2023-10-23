package com.tasks.repository;

import com.tasks.model.Task;
import com.tasks.utils.TaskUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryUnitTest {


    @Autowired
    TaskRepository repository;

    @Test
    public void should_find_no_tasks_if_repository_is_empty() {
        Iterable tasks = repository.findAll();
        assertThat(tasks).isEmpty();
    }

    @Test
    public void should_save_an_task() {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);

        Task task = new Task("Process Events","Prcesses Daily Events",false,createdDate,updatedDate);
        Task savedTask = repository.save(task);
        assertThat(savedTask).hasFieldOrPropertyWithValue("description", task.getDescription());
        assertThat(savedTask).hasFieldOrPropertyWithValue("title", task.getTitle());
        assertThat(savedTask).hasFieldOrPropertyWithValue("completed", task.isCompleted());
        assertThat(savedTask).hasFieldOrPropertyWithValue("createdDate", task.getCreatedDate());
        assertThat(savedTask).hasFieldOrPropertyWithValue("completedDate", task.getCompletedDate());
    }
    @Test
    public void should_find_all_tasks() {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Prcesses Daily Events for samsung",false,createdDate,updatedDate);
        Task vizioTask = new Task("Process Vizio Events","Prcesses Daily Events for vizio",false,createdDate,updatedDate);
        Task ctvTask = new Task("Process CTV Events","Prcesses Daily Events for ctv",false,createdDate,updatedDate);

        repository.save(samsungTask);
        repository.save(vizioTask);
        repository.save(ctvTask);
        Iterable tasks = repository.findAll();
        assertThat(tasks).hasSize(3).contains(samsungTask, vizioTask, ctvTask);
    }
    @Test
    public void should_find_task_by_id() {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Prcesses Daily Events for samsung",false,createdDate,updatedDate);
        Task vizioTask = new Task("Process Vizio Events","Prcesses Daily Events for vizio",false,createdDate,updatedDate);
        Task ctvTask = new Task("Process CTV Events","Prcesses Daily Events for ctv",false,createdDate,updatedDate);
        repository.save(samsungTask);
        repository.save(vizioTask);
        repository.save(ctvTask);
        Task foundSoapTask = repository.findById(samsungTask.getId()).get();
        assertThat(foundSoapTask).isEqualTo(samsungTask);
    }
    @Test
    public void should_update_task_by_id() {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Prcesses Daily Events for samsung",false,createdDate,createdDate);
        repository.save(samsungTask);
        Task updatedTask = repository.findById(samsungTask.getId()).get();
        updatedTask.setCompleted(true);
        updatedTask.setCompletedDate(updatedDate);
        Task savedTask = repository.save(updatedTask);
        assertThat(savedTask).hasFieldOrPropertyWithValue("completed", updatedTask.isCompleted());
        assertThat(savedTask).hasFieldOrPropertyWithValue("completedDate", updatedTask.getCompletedDate());
    }
    @Test
    public void should_delete_task_by_id() {
        Date createdDate = TaskUtils.getDate(2021,12,9,12);
        Date updatedDate = TaskUtils.getDate(2021,12,12,12);
        Task samsungTask = new Task("Process Samsung Events","Prcesses Daily Events for samsung",false,createdDate,updatedDate);
        Task vizioTask = new Task("Process Vizio Events","Prcesses Daily Events for vizio",false,createdDate,updatedDate);
        Task ctvTask = new Task("Process CTV Events","Prcesses Daily Events for ctv",false,createdDate,updatedDate);
        repository.save(samsungTask);
        repository.save(vizioTask);
        repository.save(ctvTask);
        repository.deleteById(samsungTask.getId());
        Iterable tasks = repository.findAll();
        assertThat(tasks).hasSize(2).contains(vizioTask, ctvTask);
    }
}
