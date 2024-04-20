package com.example.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter

@Setter
public class RequestDataDTO {


  @JsonProperty("reserve_date")
  private String reserve_date;


  @JsonProperty("total_count")
  private int total_count;


  // Getter와 Setter


}
