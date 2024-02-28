package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CustomerCount")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"count_id", "reserve"})
public class CustomerCount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long count_id;

  @ManyToOne
  @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id")
  private Reserve reserve;


  private int adults_count;

  private int children_count;

  private int infants_count;


  public CustomerCount(Reserve reserve, int adults_count, int children_count, int infants_count) {
    this.reserve = reserve;
    this.adults_count = adults_count;
    this.children_count = children_count;
    this.infants_count = infants_count;
  }
}
