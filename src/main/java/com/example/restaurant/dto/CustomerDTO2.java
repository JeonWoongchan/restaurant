package com.example.restaurant.dto;


import lombok.*;

@Getter
@Builder

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO2 {


  private Long id;
  private String email;
  private String password;
  private String username;
  private String phone;
  private int point;


}
