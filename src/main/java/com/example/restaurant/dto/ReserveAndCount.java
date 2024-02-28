package com.example.restaurant.dto;

import com.example.restaurant.entity.CustomerCount;
import com.example.restaurant.entity.Reserve;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReserveAndCount {
  private Reserve reserve;
  private CustomerCount customerCount;

  // Getters and setters
}