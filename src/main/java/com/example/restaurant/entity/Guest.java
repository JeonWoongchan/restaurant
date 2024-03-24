package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class Guest {

    @Column(name = "guest_id")
    @Id
    private Long guest_id;

    @OneToMany(mappedBy = "guest")
    private  List<GReserve> reserveGuest = new ArrayList<>();;



    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;





    public Guest(Long guest_id,String name,String phone) {
        this.guest_id = guest_id;
        this.name = name;

        this.phone = phone;
    }

    public Guest(Long guest_id) {
        this.guest_id = guest_id;
    }
}
