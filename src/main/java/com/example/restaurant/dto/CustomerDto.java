package com.example.restaurant.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private  String email;
    private String username;
    private String phone;
    private int point;
    private String password;

    public CustomerDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public CustomerDto() {

    }

    public CustomerDto(String email) {
        this.email = email;
    }
}
