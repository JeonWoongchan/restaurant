package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

  public Reserve(Customer customer, Date reg_date, Date reserve_date, Date end_date, String comment) {
    this.customer = customer;
    this.reg_date = reg_date;
    this.reserve_date = reserve_date;
    this.end_date = end_date;
    this.comment = comment;
  }
}
