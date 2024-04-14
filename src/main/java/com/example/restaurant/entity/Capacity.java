package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "capacity")
@Getter
@Setter
public class Capacity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String hour_time;

  @Column(nullable = false)
  private int capacity;

  // 생성자, 게터, 세터, 기타 필요한 메서드
}
