package com.example.restaurant.repository;

import com.example.restaurant.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback(value = false)
class AdminRepositoryTest {
    @Autowired
    AdminRepository adminRepository;
    @Test
    public void save() {
        Manager admin = new Manager("kksos28", "rbtlr1234");

        adminRepository.save(admin);
    }
    @Test
    public void findByManagerIdAndAndPassword() {

       String managerID = "kksos28";

       String password = "rbtlr12345";


        Optional<Manager> loginCheck = adminRepository.findByManagerIdAndAndPassword(managerID, password);
        if (loginCheck.isPresent()) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }


    }







}