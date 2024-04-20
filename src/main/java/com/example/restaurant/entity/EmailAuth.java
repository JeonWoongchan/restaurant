package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EmailAuth")
@Getter
@Setter
public class EmailAuth {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String auth;

  // 생성자, 게터, 세터, 기타 필요한 메서드
}
