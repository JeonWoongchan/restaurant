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
        Customer customer = new Customer("memberA");
        Customer saved = customerJpaRepository.save(customer);

        Customer findMember = customerJpaRepository.find(saved.getId());

        assertThat(findMember.getId()).isEqualTo(customer.getId());
        assertThat(findMember.getUsername()).isEqualTo(customer.getUsername());
        assertThat(findMember).isEqualTo(customer);
    }
    @Test
    public  void basicCRUD() {
        Customer customer1 = new Customer("customer1");
        Customer customer2 = new Customer("customer2");


        customerJpaRepository.save(customer1);
        customerJpaRepository.save(customer2);

        // 단건조회 검증
        Customer findCustomer1 = customerJpaRepository.findById(customer1.getId()).get();
        Customer findCustomer2 = customerJpaRepository.findById(customer2.getId()).get();

        assertThat(findCustomer1).isEqualTo(customer1);
        assertThat(findCustomer2).isEqualTo(customer2);


        List<Customer> all = customerJpaRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //카운트 검정
        long count = customerJpaRepository.count();
        assertThat(count).isEqualTo(2);

        customerJpaRepository.delete(customer1);
        customerJpaRepository.delete(customer2);

        long deletedcount = customerJpaRepository.count();
        assertThat(deletedcount).isEqualTo(0);
    }
    @Test
    public void findByUsernameAndPointGreaterThen() {
        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",1 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerJpaRepository.save(c1);
        customerJpaRepository.save(c2);


        List<Customer> result = customerJpaRepository.findByUsernameAndPointGreaterThen("AAA", 15);

        assertThat(result.get(0).getPoint()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public  void testNamed() {
        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",1 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerJpaRepository.save(c1);
        customerJpaRepository.save(c2);


        List<Customer> result = customerJpaRepository.findByUsername("AAA");
        Customer findMember = result.get(0);
        assertThat(findMember).isEqualTo(c1);


    }

    @Test
    public  void paging() {


        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10));
        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10 ));
        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10));
        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10 ));
        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10 ));
        customerJpaRepository.save(new Customer("customer1","1234","AAA@naver.com","010-1111-1111",10 ));

        int point =10;
        int offset = 0;
        int limit = 3;

        //when

        List<Customer> members = customerJpaRepository.findByPage(point, offset, limit);
        long totalCount = customerJpaRepository.totalCount(point);

        // 페이지 계산 공식
        // totalPage = totalcount /size...
        // 마지막 페이지
        // 최초 페이지

        //then

        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(6);

    }


}