package com.example.restaurant.repository;

import com.example.restaurant.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GusetRepository extends JpaRepository<Guest,Long> {
}
