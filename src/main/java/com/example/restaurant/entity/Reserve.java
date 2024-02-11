package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"",""})
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserve_id;
    private Long customer_id;
    private Long rest_id;
    private Long seat_id;
    private Date reserve_date;
    private Date start_date;
    private Date end_date;




}
