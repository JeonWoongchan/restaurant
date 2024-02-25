package com.example.restaurant.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private  String email;
    private String password;
    private String username;

    private String inputAuth;
    private String phoneNUm;


    public CustomerDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
