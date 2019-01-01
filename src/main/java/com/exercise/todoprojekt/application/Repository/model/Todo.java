package com.exercise.todoprojekt.application.Repository.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Boolean urgent = false;
    private Boolean done = false;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
    private String description;

    @ManyToOne(fetch = FetchType.EAGER) // ugyan azon kérdések mint az Assigneenél
    @JoinColumn(name = "assignee_id")
    protected Assignee assignee;


    public Todo() {
        urgent = false;
        done = false;
        date = new Date();
        description = "";
    }


    public Todo( String title, Boolean urgent, Boolean done,String description) {
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        this.description = description;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Date getDate() {
        return date;
    }



    public Todo(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public Boolean getDone() {
        return done;
    }
}
