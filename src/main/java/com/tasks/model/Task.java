package com.tasks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private boolean completed;
    @NotNull(message = "Created date can not be null or empty")
    @PastOrPresent(message="Created date  must be less than or equals to today's date")
    private Date createdDate ;
    @PastOrPresent(message="Completed date  must be less than or equals to today's date")
    private Date completedDate;
    @NotBlank(message = "Title can not be null or empty")
    @NotNull(message = "Title can not be null or empty")
    private String title;
    @NotBlank(message = "Description can not be null or empty")
    @NotNull(message = "Description can not be null or empty")
    private String description;

    public Task(){

    }
    public Task(String title, String description, boolean completed, Date createdDate, Date completedDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdDate = createdDate;
        this.completedDate = completedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
