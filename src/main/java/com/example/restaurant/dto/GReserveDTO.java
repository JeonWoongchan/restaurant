package com.example.restaurant.dto;

public class GReserveDTO {

  private String reg_date;
  private String reserve_date;
  private String end_date;
  private int adults_count;
  private int children_count;
  private int infants_count;
  public GReserveDTO(String reg_date, String reserve_date, String end_date, int adults_count, int children_count, int infants_count) {
    this.reg_date = reg_date;
    this.reserve_date = reserve_date;
    this.end_date = end_date;
    this.adults_count = adults_count;
    this.children_count = children_count;
    this.infants_count = infants_count;
  }



}
