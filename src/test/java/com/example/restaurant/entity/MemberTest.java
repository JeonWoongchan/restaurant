package com.example.restaurant.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(false)
class Rest_MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntitiy() {
        Team teamA =new Team("teamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Customer member1 = new Customer("member1",10,teamA);
        Customer member2 = new Customer("member2",20,teamA);
        Customer member3 = new Customer("member3",30,teamB);
        Customer member4 = new Customer("member4",40,teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);


        em.flush();
        em.clear();

       em.close();

       List<Customer> members = em.createQuery("select m from Customer m ", Customer.class).getResultList();

        for (Customer member : members) {
            System.out.println("member =  "+member);
            System.out.println("member.team =  "+member.getTeam());
            
        }
    }
}