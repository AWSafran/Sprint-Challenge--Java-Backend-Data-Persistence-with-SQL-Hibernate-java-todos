package com.sprint.data.service;

import com.sprint.data.model.Todo;
import com.sprint.data.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service("todoService")
public class TodoServiceImpl implements TodoService
{
    @Autowired
    private TodoRepository todoRepository;
    
    @Transactional
    @Override
    public Todo updateTodo(Todo todo, long todoid) throws EntityNotFoundException
    {
        if(todoRepository.findById(todoid).isPresent())
        {
            todoRepository.updateTodo(todo.getDescription(), todoid);
            return todo;
        } else
        {
            throw new EntityNotFoundException();
        }
    }
}
