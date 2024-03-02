package com.example.restaurant.repository;

import com.example.restaurant.entity.CustomerCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface CustomerCountRepository extends JpaRepository<CustomerCount, Long> {


}
