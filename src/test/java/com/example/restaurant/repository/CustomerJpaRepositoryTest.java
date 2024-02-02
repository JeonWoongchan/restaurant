package com.example.restaurant.repository;


import com.example.restaurant.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback(value = false)
@Transactional
@SpringBootTest
class CustomerJpaRepositoryTest {
    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Test
    public  void  testMember() {
        Customer member = new Customer("memberA");
        Customer saved = customerJpaRepository.save(member);

        Customer findMember = customerJpaRepository.find(saved.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }
    @Test
    public  void basicCRUD() {
        Customer member1 = new Customer("member1");
        Customer member2 = new Customer("member2");


        customerJpaRepository.save(member1);
        customerJpaRepository.save(member2);

        // 단건조회 검증
        Customer findMember1 = customerJpaRepository.findById(member1.getId()).get();
        Customer findMember2 = customerJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);


        List<Customer> all = customerJpaRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //카운트 검정
        long count = customerJpaRepository.count();
        assertThat(count).isEqualTo(2);

        customerJpaRepository.delete(member1);
        customerJpaRepository.delete(member2);

        long deletedcount = customerJpaRepository.count();
        assertThat(deletedcount).isEqualTo(0);
    }
    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("AAA", 20);


        customerJpaRepository.save(m1);
        customerJpaRepository.save(m2);


        List<Customer> result = customerJpaRepository.findByUsernameAndAgeGreaterThen("AAA", 15);

        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public  void testNamed() {
        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("AAA", 20);


        customerJpaRepository.save(m1);
        customerJpaRepository.save(m2);


        List<Customer> result = customerJpaRepository.findByUsername("AAA");
        Customer findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);


    }

    @Test
    public  void paging() {


        customerJpaRepository.save(new Customer("member1",10));
        customerJpaRepository.save(new Customer("member2",10));
        customerJpaRepository.save(new Customer("member3",10));
        customerJpaRepository.save(new Customer("member4",10));
        customerJpaRepository.save(new Customer("member5",10));
        customerJpaRepository.save(new Customer("member6",10));

        int age =10;
        int offset = 0;
        int limit = 3;

        //when

        List<Customer> members = customerJpaRepository.findByPage(age, offset, limit);
        long totalCount = customerJpaRepository.totalCount(age);

        // 페이지 계산 공식
        // totalPage = totalcount /size...
        // 마지막 페이지
        // 최초 페이지

        //then

        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(6);

    }


}