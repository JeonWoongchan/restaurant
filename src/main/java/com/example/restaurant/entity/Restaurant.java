package com.example.restaurant.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"rest_id","restname","rest_address","rest_contact"})
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long rest_id;
    private String restname;
    private String rest_address;
    private String rest_contact;
    private int maxSeat;



    @OneToMany(mappedBy = "restaurant")
    private List<Seat> restaurants =new ArrayList<>();



    public Restaurant(String restname, String rest_address, String rest_contact ,int maxSeat) {
        this.restname = restname;
        this.rest_address = rest_address;
        this.rest_contact = rest_contact;
        this.maxSeat = maxSeat;
    }


}
