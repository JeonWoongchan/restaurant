package com.example.restaurant.repository;

import com.example.restaurant.entity.CustomerCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface CustomerCountRepository extends JpaRepository<CustomerCount, Long> {


}
