package com.myFirstTask.authApi.entity;

import lombok.Data;

import java.util.Date;

public class RegisterRequest {
    private String Name;
    private String Email;
    private String Password;
    private Date DOB;

    public RegisterRequest(String name, String email, String password, Date DOB) {
        Name = name;
        Email = email;
        Password = password;
        this.DOB = DOB;
    }

    public RegisterRequest(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
}
