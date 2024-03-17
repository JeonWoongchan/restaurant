package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Guset")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"guest_id","reserveGuest","phone"})

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guest_id;

    @ManyToOne
    @JoinColumn(name = "reserve_guest_id", referencedColumnName = "reserve_guest_id")
    private ReserveGuest reserveGuest;


    private String phone;


}
