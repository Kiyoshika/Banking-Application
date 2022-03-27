package com.zweaver.bankingapp.entity;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="username", length=30, nullable=false, unique=true)
    private String username;

    @Column(name="password", length=120, nullable=false, unique=false)
    private String password;

    public UserEntity() {}
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
