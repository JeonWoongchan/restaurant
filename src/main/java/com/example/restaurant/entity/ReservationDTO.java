package com.example.restaurant.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationDTO {
  private Reserve reserve;
  private Guest guest;
  private ReserveGuest reserveGuest;

  // 생성자, 게터, 세터 등 필요한 메서드를 추가할 수 있음
}