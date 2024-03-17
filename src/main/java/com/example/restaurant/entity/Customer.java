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
@ToString(of = { "id", "email", "password", "username", "phone", "point" })
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

    @Column(name = "phone")
    private String phone;

    @Column(name = "point")
    private int point;

    @Column(name = "refresh-token")

    @OneToMany(mappedBy = "customer")
    private List<Reserve> reserves = new ArrayList<>();

    public Customer(String username) {
        this.username = username;
    }

    public Customer(String username, String password, String email, String phone, int point) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.point = point;
    }

    public Customer(String username, int point) {
        this.username = username;
        this.point = point;
    }

    public Customer(Long id, String email, String password, String username, String phone, int point) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.point = point;

    }

    public Customer(Long id) {
        this.id = id;
    }
}
