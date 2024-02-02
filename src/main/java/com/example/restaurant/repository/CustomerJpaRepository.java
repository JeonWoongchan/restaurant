package com.example.restaurant.repository;

import com.example.restaurant.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class CustomerJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Customer save(Customer member) {
        em.persist(member);
        return member;
    }

    public void delete(Customer member){
        em.remove(member);
    }

    public List<Customer> findAll() {
        return em.createQuery("select r from Customer r", Customer.class).getResultList();
    }
    public Optional<Customer> findById(Long id) {
        Customer member = em.find(Customer.class, id);
        return Optional.ofNullable(member);

    }

    public long count() {
        return  em.createQuery("select  count(r) from Customer r", Long.class).getSingleResult();
    }

    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findByUsernameAndAgeGreaterThen(String username, int age) {
        return em.createQuery("select  r from Customer r where r.username = :username  and r.age > :age")
                .setParameter("username", username)
                .setParameter("age",age)
                .getResultList();
    }
    public List<Customer> findByUsername(String username) {
       return em.createNamedQuery("RestMember.findByUsername", Customer.class)
                .setParameter("username", username)
                .getResultList();
    }


    public  List<Customer> findByPage(int age, int offset , int limit) {
         return  em.createQuery("select r from Customer r where r.age = :age order by r.username desc")
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public  long totalCount(int age) {
        return em.createQuery("select count(r) from Customer r where r.age= :age", Long.class)
                .setParameter("age",age)
                .getSingleResult();
    }


}
