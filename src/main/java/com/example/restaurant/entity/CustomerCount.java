package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CustomerCount")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"reserve_guest_id", "customer"})
public class CustomerCount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reserve_guest_id;

  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  private Customer customer;


}
