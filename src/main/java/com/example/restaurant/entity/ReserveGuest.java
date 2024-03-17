package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "ReserveGuest")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"reserve_guest_id", "guest"})
public class ReserveGuest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reserve_guest_id;

  @ManyToOne
  @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
  private Guest guest;

  private String reg_date;
  private String reserve_date;
  private String end_date;
  private int adults_count;
  private int children_count;
  private int infants_count;
  private String comment;


}
