package com.example.restaurant.dto;

import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Guest;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.entity.ReserveGuest;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReserveDTO {
  private Reserve reserve;

  private Guest guest;
  private ReserveGuest reserveGuest;
  // 필요한 생성자, 게터, 세터 등을 추가합니다.
}