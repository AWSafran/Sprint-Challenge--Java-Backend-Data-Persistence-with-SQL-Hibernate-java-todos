package com.sprint.data;

import com.sprint.data.model.Role;
import com.sprint.data.model.Todo;
import com.sprint.data.model.User;
import com.sprint.data.model.UserRoles;
import com.sprint.data.repository.RoleRepository;
import com.sprint.data.repository.TodoRepository;
import com.sprint.data.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    TodoRepository todorepos;
    
    
    public SeedData(RoleRepository rolerepos, UserRepository userrepos, TodoRepository todorepos)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.todorepos = todorepos;
    }
    
    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("zoodata");
        Role r3 = new Role("animaldata");
        Role r4 = new Role("mgr");
        
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        
        ArrayList<UserRoles> zoodata = new ArrayList<>();
        zoodata.add(new UserRoles(new User(), r2));
        
        ArrayList<UserRoles> animaldata = new ArrayList<>();
        animaldata.add(new UserRoles(new User(), r3));
        
        ArrayList<UserRoles> mgr = new ArrayList<>();
        mgr.add(new UserRoles(new User(), r4));
        
        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);
        rolerepos.save(r4);
        
        User u1 = new User("userone", "password", zoodata);
        
        User u2 = new User("admin", "password", admins);
        
        User u3 = new User("usertwo", "password", animaldata);
        
        User u4 = new User("userthree", "password", mgr);
        
        
        
        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
        userrepos.save(u4);
    
        Todo t1 = new Todo("Test Code", u2);
        
        todorepos.save(t1);
    }
}
