package com.sprint.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoid;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties("todos")
    private User user;
    
    public Todo()
    {
    }
    
    public Todo(String description, User user)
    {
        this.description = description;
        this.user = user;
    }
    
    public long getTodoid()
    {
        return todoid;
    }
    
    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public User getUser()
    {
        return user;
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
}
