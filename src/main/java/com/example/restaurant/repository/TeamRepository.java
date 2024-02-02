package com.example.restaurant.repository;

import com.example.restaurant.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, Long> {
}
