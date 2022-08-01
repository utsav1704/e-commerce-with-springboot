package com.example.ecommerce.ECommerce.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    public int sid;

    @NotNull
    @Column(name = "name")
    public String name;

    @Column(name = "email",nullable = false,unique = true)
    public String email;

    @Column(name = "password")
    public String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_SID",referencedColumnName = "SID")},
            inverseJoinColumns = {@JoinColumn (name = "ROLE_ID",referencedColumnName = "ID")}
    )
    public List<Role> roles;

    public User() {
    }

    public User(int sid, String name, String email, String password, List<Role> roles) {
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(User u){
        this.sid = u.getSid();
        this.name = u.getName();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.roles = u.getRoles();
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
