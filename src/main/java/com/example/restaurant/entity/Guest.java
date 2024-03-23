package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"guest_id","reserveGuest","phone"})

public class Guest {
    @Id
    private Long guest_id;




    private String phone;

    @OneToMany(mappedBy = "guest")
    private  List<GReserve> reserveGuest = new ArrayList<>();;

    public Guest(Long guest_id, String phone) {
        this.guest_id = guest_id;
        this.phone = phone;
    }
}
