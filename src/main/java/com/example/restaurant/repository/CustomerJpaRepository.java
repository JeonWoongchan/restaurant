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

    public Customer save(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public void delete(Customer customer){
        em.remove(customer);
    }

    public List<Customer> findAll() {
        return em.createQuery("select r from Customer r", Customer.class).getResultList();
    }
    public Optional<Customer> findById(Long id) {
        Customer customer = em.find(Customer.class, id);
        return Optional.ofNullable(customer);

    }

    public long count() {
        return  em.createQuery("select  count(r) from Customer r", Long.class).getSingleResult();
    }

    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findByUsernameAndPointGreaterThen(String username, int point) {
        return em.createQuery("select  c from Customer c where r.username = :username  and r.age > :age")
                .setParameter("username", username)
                .setParameter("point",point)
                .getResultList();
    }
    public List<Customer> findByUsername(String username) {
       return em.createNamedQuery("Customer.findByUsername", Customer.class)
                .setParameter("username", username)
                .getResultList();
    }


    public  List<Customer> findByPage(int point, int offset , int limit) {
         return  em.createQuery("select r from Customer r where r.point = :point order by r.username desc")
                .setParameter("point", point)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public  long totalCount(int point) {
        return em.createQuery("select count(r) from Customer r where r.point= :point", Long.class)
                .setParameter("point",point)
                .getSingleResult();
    }


}
