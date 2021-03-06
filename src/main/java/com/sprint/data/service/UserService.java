package com.sprint.data.service;


import com.sprint.data.model.User;

import java.util.List;

public interface UserService {
    
    List<User> findAll();
    
    User findUserByName(String name);
    
    User findUserById(long id);
    
    void delete(long id);
    
    User save(User user, String password);
    
    User update(User user, long id);
}