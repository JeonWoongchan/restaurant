package com.example.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Seat")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(of = {"seat_id", "seat_number", "capacity"})
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seat_id;

    @Column(name = "seat_number")
    private int seat_number;

    private int guest_count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    // 생성자, 게터/세터, 기타 메서드 생략




    public Seat(int seat_number, int guest_count, Restaurant restaurant) {
        this.seat_number = seat_number;
        this.guest_count = guest_count;
        this.restaurant = restaurant;
        if (restaurant != null) {
            changeRestaurant(restaurant);
        }
    }







    public void changeRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.getRestaurants().add(this);
    }


}
