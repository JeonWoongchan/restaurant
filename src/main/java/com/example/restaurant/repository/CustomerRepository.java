package com.example.restaurant.repository;

import com.example.restaurant.dto.CustomerDto;
import com.example.restaurant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // 간단
    List<Customer> findByUsernameAndPointGreaterThan(String username, int point);

    @Query("SELECT c.id FROM Customer c WHERE c.email = :email")
    Optional<Customer> findByIdtoEmail(@Param("email") String email);

    List<Customer> findHelloBy();

    @Query("select c.username from Customer  c where  c.email = :email ")
    Optional<String> findByname(@Param("email") String email);

    // 복잡
    @Query("select c from Customer  c where  c.username = :username and c.point = :point")
    List<Customer> findUser(@Param("username") String username, @Param("point") int point);

    @Query("select c.id,c.email,c.point,c.point,c.phone from Customer  c where  c.email=:email")
    Optional<Customer> findByEmail(@Param("email") String email);

    @Query("select c from Customer c where c.email = :email ")
    Optional<Customer> login(@Param("email") String email);

    @Query("select c.username from Customer  c")
    List<String> findUsernameList();

    // @Query("select new com.example.restaurant.dto.CustomerDto (c.id, c.username,
    // t.name)from Customer c join c.team t")
    // List<CustomerDto> findCustomerDto();

    @Query("select c from Customer c where c.username in :names")
    List<Customer> findByNames(@Param("names") List<String> names);

    List<Customer> findListByUsername(String username);

    Customer findCustomerByUsername(String username);

    Optional<Customer> findOptionalByUsername(String username);
}
