package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="Reserve")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"reserve_id", "customer" ,"reserve_date" ,"end_date" ,"comment"})
public class Reserve {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reserve_id;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private Customer customer;

  private Date reg_date;
  private Date reserve_date;
  private Date end_date;
  private String comment;
}
