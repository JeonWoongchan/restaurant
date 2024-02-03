package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@NamedQuery(
        name="Customer.findByUsername",
        query = "select c from Customer c where username = :username"
)
@Entity
@Table(name = "Customer")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "password","email","phone_number","point"})
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone_number;
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
