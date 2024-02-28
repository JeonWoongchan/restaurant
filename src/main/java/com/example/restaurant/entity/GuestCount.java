package com.example.restaurant.entity;


import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "CustomerCount")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"guest_count_id", "reserve" })
public class GuestCount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long guest_count_id;

  @ManyToOne
  @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id")
  private Reserve reserve;


  private int audults_count;

  private int childrend_count;

  private int infants_count;

}
