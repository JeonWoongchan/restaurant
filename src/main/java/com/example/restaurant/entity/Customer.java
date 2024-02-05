package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NamedQuery(
        name="Customer.findByUsername",
        query = "select c from Customer c where username = :username"
)
@Entity
@Table(name = "Customer")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "email", "password","username","phone_number","point"})
public class Customer {

    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "point")
    private int point;


    public Customer(String username) {
        this.username = username;
    }


    public Customer(String username, String password, String email, String phone_number, int point) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.point = point;
    }

    public Customer(String username, int point) {
        this.username = username;
        this.point = point;
    }
}
