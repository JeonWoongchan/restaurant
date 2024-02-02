package com.example.restaurant.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private  Long id;
    private String username;
    private String teamName;

    public CustomerDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
