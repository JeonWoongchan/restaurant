package com.example.restaurant.dto;

import com.example.restaurant.entity.Guest;
import com.example.restaurant.entity.Reserve;
import com.example.restaurant.entity.GReserve;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReserveAndGusetDTO {
  private Reserve reserve;

  private Guest guest;
  private GReserve greserve;
  // 필요한 생성자, 게터, 세터 등을 추가합니다.
}