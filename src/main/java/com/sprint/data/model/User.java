package com.sprint.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Todo> todos;
    
    public User()
    {
    }
    
    public User(String username, String password, List<UserRoles> userRoles)
    {
        this.username = username;
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
        
        for(UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }
    
    public List<Todo> getTodos()
    {
        return todos;
    }
    
    public void setTodos(List<Todo> todos)
    {
        this.todos = todos;
    }
    
    public long getUserid()
    {
        return userid;
    }
    
    public void setUserid(long userid)
    {
        this.userid = userid;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        this.password = passwordEncoder.encode(password);
    }
    
    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }
    
    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }
    
    public void setPasswordAlreadyEncrypted(String encryptedPassword)
    {
        this.password = encryptedPassword;
    }
    
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();
        
        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        
        return rtnList;
    }
    
    @Override
    public String toString()
    {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", todos=" + todos +
                '}';
    }
}

