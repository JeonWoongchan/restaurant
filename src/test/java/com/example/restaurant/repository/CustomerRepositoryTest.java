package com.example.restaurant.repository;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
   // @Autowired TeamRepository teamRepository;

    @Test
    public void testCustomer() {
        Customer customer = new Customer("customerA");
        Customer saved = customerRepository.save(customer);

        Customer findMember = customerRepository.findById(saved.getId()).get();

        assertThat(findMember.getId()).isEqualTo(customer.getId());
        assertThat(findMember.getUsername()).isEqualTo(customer.getUsername());
        assertThat(findMember).isEqualTo(customer);
    }

    @Test
    public  void basicCRUD() {
        Customer customer1 = new Customer("customer1");
        Customer customer2 = new Customer("customer2");


        customerRepository.save(customer1);
        customerRepository.save(customer2);

        // 단건조회 검증
        Customer findMember1 = customerRepository.findById(customer1.getId()).get();
        Customer findMember2 = customerRepository.findById(customer2.getId()).get();

        assertThat(findMember1).isEqualTo(customer1);
        assertThat(findMember2).isEqualTo(customer2);


        List<Customer> all = customerRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //카운트 검정
        long count = customerRepository.count();
        assertThat(count).isEqualTo(2);

        customerRepository.delete(customer1);
        customerRepository.delete(customer2);

        long deletedcount = customerRepository.count();
        assertThat(deletedcount).isEqualTo(0);
    }


    @Test
    public void findByUsernameAndPointGreaterThan() {
        Customer c1 = new Customer("AAA", 10);
        Customer c2 = new Customer("AAA", 20);


        customerRepository.save(c1);
        customerRepository.save(c2);


        List<Customer> result = customerRepository.findByUsernameAndPointGreaterThan("AAA", 15);

        assertThat(result.get(0).getPoint()).isEqualTo(20);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public  void findHelloBy() {
        List<Customer> members = customerRepository.findHelloBy();
    }


    @Test
    public  void testNamed() {
        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerRepository.save(c1);
        customerRepository.save(c2);


        List<Customer> result = customerRepository.findByUsername("AAA");
        Customer findCustomer = result.get(0);
        assertThat(findCustomer).isEqualTo(c1);


    }


    @Test
    public  void findUser() {
        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerRepository.save(c1);
        customerRepository.save(c2);


        List<Customer> result = customerRepository.findUser("AAA" ,5);
        assertThat(result.get(0)).isEqualTo(c1);


    }
    @Test
    public  void  findUsernameList() {

        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerRepository.save(c1);
        customerRepository.save(c2);


        List<String> usernameList = customerRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }


    }

    @Test
    public  void  findByNames() {

        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );
        Customer c2 = new Customer("BBB","1234","AAA@naver.com","010-1111-1111",5 );

        customerRepository.save(c1);
        customerRepository.save(c2);


        List<Customer> result = customerRepository.findByNames(Arrays.asList("AAA","BBB"));
        for (Customer m : result) {
            System.out.println("s = " + m);
        }




    }


    @Test
    public  void  returnType() {

        Customer c1 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );
        Customer c2 = new Customer("AAA","1234","AAA@naver.com","010-1111-1111",5 );


        customerRepository.save(c1);
        customerRepository.save(c2);


        List<Customer> result = customerRepository.findListByUsername(("AAA"));
        for (Customer m : result) {
            System.out.println("s = " + m);
        }




    }
}