package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"", ""})
public class Reserve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reserve_id;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private Customer customer;

  private Date reserve_date;
  private Date start_date;
  private Date end_date;
}
