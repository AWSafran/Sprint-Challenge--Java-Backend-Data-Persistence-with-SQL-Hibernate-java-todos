package com.sprint.data.controller;

import com.sprint.data.model.Todo;
import com.sprint.data.model.User;
import com.sprint.data.service.TodoService;
import com.sprint.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class Controller
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private TodoService todoService;
    
    // http://localhost:2019/users/mine
    // get active user and their todos
    @GetMapping(value = "/users/mine", produces = {"application/json"})
    private ResponseEntity<?> getActiveUser(Authentication authentication)
    {
        String currentUserName = authentication.getName();
        System.out.println(currentUserName);
        
        return new ResponseEntity<>(userService.findUserByName(currentUserName), HttpStatus.OK);
    }
    
    // http://localhost:2019/users
    // add new user
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/users", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addUser(@RequestBody User newUser) throws URISyntaxException
    {
//        newUser = userService.save(newUser, newUser.getPassword());
        // todo Still need to figure out how to add userRoles to this. will come back
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
    
    // http://localhost:2019/users/todo
    // add a 2do to the assigned user -- this is tested in postman as a string with no quotes/braces
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/users/todo/{userid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addTodo(@RequestBody String description, @PathVariable long userid)
    {
        Todo newTodo = new Todo(description, userService.findUserById(userid));
    
        todoService.save(newTodo);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // http://localhost:2019/todos/todoid/9
    // update the description of an existing todolist item
    @PutMapping(value = "/todos/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo newTodo, @PathVariable long todoid)
    {
        Todo updatedTodo = todoService.updateTodo(newTodo, todoid);
        
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
    
    // http://localhost:2019/users/userid/{userid}
    // delete a user and associated 2dos
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/userid/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid)
    {
        List<Todo> todosFromUser = userService.findUserById(userid).getTodos();
        if(todosFromUser.size() != 0)
        {
            for(Todo t : todosFromUser)
            {
                todoService.delete(t);
            }
            
        }
    
        userService.delete(userid);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
