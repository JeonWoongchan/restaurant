package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "Customer.findByUsername", query = "select c from Customer c where username = :username")
@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "customer_id", "email", "password", "username", "phone", "point" })
public class Customer {

    @Column(name = "customer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @Column(name = "point")
    private int point;


    @OneToMany(mappedBy = "customer")
    private List<Reserve> reserves = new ArrayList<>();

    public Customer(String username) {
        this.username = username;
    }



    public Customer(String username, int point) {
        this.username = username;
        this.point = point;
    }



    public Customer(String email, String username, String phone, int point) {
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.point = point;
    }

    public Customer(Long customer_id, String email, String password, String username, String phone, int point) {
        this.customer_id = customer_id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.point = point;
    }

    public Customer(Long customer_id) {
        this.customer_id = customer_id;
    }
}
