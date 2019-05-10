package com.sprint.data.service;

import com.sprint.data.model.Todo;

public interface TodoService
{
    public Todo updateTodo(Todo todo, long todoid);
    
    public void save(Todo todo);
}
