package com.example.restaurant.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReserveDTO {

  private Long reserve_id;
  private String reg_date;
  private String reserve_date;
  private String end_date;
  private int adults_count;
  private int children_count;
  private int infants_count;

  public ReserveDTO(Long reserve_id) {
    this.reserve_id = reserve_id;
  }

  private String name; // 추가된 필드
  private String phone;

  public ReserveDTO(Long reserve_id, String reg_date, String reserve_date, String end_date, int adults_count, int children_count, int infants_count, String name, String phone) {
    this.reserve_id = reserve_id;
    this.reg_date = reg_date;
    this.reserve_date = reserve_date;
    this.end_date = end_date;
    this.adults_count = adults_count;
    this.children_count = children_count;
    this.infants_count = infants_count;
    this.name = name;
    this.phone = phone;
  }
}
