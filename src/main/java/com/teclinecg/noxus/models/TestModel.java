package com.teclinecg.noxus.models;

import jakarta.validation.constraints.Email;

public class TestModel {

    @Email
    private String email;

    public TestModel() {
    }

    public TestModel(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
