package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "Reserve")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "reserve_id", "customer", "reserve_date", "end_date", "comment" })
public class Reserve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reserve_id;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private Customer customer;

  private String reg_date;
  private String reserve_date;
  private String end_date;

  private int adults_count;
  private int children_count;
  private int infants_count;
  private String comment;

  public Reserve(Customer customer, String reg_date, String reserve_date, String end_date, String comment) {
    this.customer = customer;
    this.reg_date = reg_date;
    this.reserve_date = reserve_date;
    this.end_date = end_date;
    this.comment = comment;
  }
}
