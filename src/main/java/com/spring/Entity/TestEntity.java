package com.spring.Entity;


import javax.persistence.*;

/**
 * Created by Arabira on 2016/9/29.
 */
@Entity
@Table(name = "test")
public class TestEntity {

    private int id;
    private String userName;
    private String password;
    private String passWord;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 200)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "pass_word")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
