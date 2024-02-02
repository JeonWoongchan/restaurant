package com.example.restaurant.repository;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.entity.Team;
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
    @Autowired TeamRepository teamRepository;

    @Test
    public void testMember() {
        Customer member = new Customer("memberA");
        Customer saved = customerRepository.save(member);

        Customer findMember = customerRepository.findById(saved.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public  void basicCRUD() {
        Customer member1 = new Customer("member1");
        Customer member2 = new Customer("member2");


        customerRepository.save(member1);
        customerRepository.save(member2);

        // 단건조회 검증
        Customer findMember1 = customerRepository.findById(member1.getId()).get();
        Customer findMember2 = customerRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);


        List<Customer> all = customerRepository.findAll();

        assertThat(all.size()).isEqualTo(2);

        //카운트 검정
        long count = customerRepository.count();
        assertThat(count).isEqualTo(2);

        customerRepository.delete(member1);
        customerRepository.delete(member2);

        long deletedcount = customerRepository.count();
        assertThat(deletedcount).isEqualTo(0);
    }


    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("AAA", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<Customer> result = customerRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public  void findHelloBy() {
        List<Customer> members = customerRepository.findHelloBy();
    }


    @Test
    public  void testNamed() {
        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("AAA", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<Customer> result = customerRepository.findByUsername("AAA");
        Customer findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);


    }


    @Test
    public  void findUser() {
        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("BBB", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<Customer> result = customerRepository.findUser("AAA" ,10);
        assertThat(result.get(0)).isEqualTo(m1);


    }
    @Test
    public  void  findUsernameList() {

        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("BBB", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<String> usernameList = customerRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }


    }
    @Test
    public  void  findMemberDto() {

        Team team = new Team("teamA");
        teamRepository.save(team);

        Customer m1 = new Customer("AAA", 10);
        m1.setTeam(team);
        customerRepository.save(m1);

        List<CustomerDto> memberDto = customerRepository.findMemberDto();
        for (CustomerDto dto : memberDto) {
            System.out.println("dto = " + dto);
            
        }



    }
    @Test
    public  void  findByNames() {

        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("BBB", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<Customer> result = customerRepository.findByNames(Arrays.asList("AAA","BBB"));
        for (Customer m : result) {
            System.out.println("s = " + m);
        }




    }


    @Test
    public  void  returnType() {

        Customer m1 = new Customer("AAA", 10);
        Customer m2 = new Customer("BBB", 20);


        customerRepository.save(m1);
        customerRepository.save(m2);


        List<Customer> result = customerRepository.findListByUsername(("AAA"));
        for (Customer m : result) {
            System.out.println("s = " + m);
        }




    }
}