package com.example.restaurant.repository;

import com.example.restaurant.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Manager,Long> {


    Optional<Manager> findByManagerIdAndAndPassword(@Param("managerID") String managerID, @Param("password") String password);





}
