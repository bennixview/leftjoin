package com.example.entity;

import com.mysema.query.jpa.impl.JPAQuery;

import org.junit.Before;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class JoinTest {


    EntityManager em;

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerFactory");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Test
    public void test() throws Exception {
        EntityA entityA = em.merge(new EntityA());

        for (int i = 0; i < 10; i++) {
            EntityB entityB = em.merge(new EntityB());
            EntityC entityC = em.merge(new EntityC());
            entityC.setValue(String.valueOf(i));
            entityB.setEntityA(entityA);
            entityB.setEntityC(entityC);
        }

        // EntityA 1--n EntityB n--1 EntityC
        em.flush();
        em.clear();

        QEntityA qEntityA = QEntityA.entityA;
        QEntityB qEntityB = QEntityB.entityB;
        QEntityC qEntityC = QEntityC.entityC;

        List<EntityA> list = new JPAQuery(em)
                .from(qEntityA)
                .leftJoin(qEntityA.entityBs, qEntityB)
                .innerJoin(qEntityB.entityC, qEntityC)     //   <<--- Is it possible to make a nested inner Join here
                .on(qEntityC.value.eq("-5"))
                .list(qEntityA);

        assertThat(list.size(), is(1));


        /*I want to say exactly that:
        select *
                from T_TEST_ENTITY_A a
        left join T_TEST_ENTITY_B b
        inner join T_TEST_ENTITY_C c
        on b.TEST_ENTITY_C = c.TEST_ID on b.TEST_ENTITY_A = a.TEST_ID
        and c.entc_value = '-5';

        or in querydsl:

        List<EntityA> list = new JPAQuery(em)
                .from(qEntityA)
                .leftJoin(qEntityA.entityBs, qEntityB, innerJoin(qEntityB.entityC, qEntityC)
                .on(qEntityC.value.eq("-5")))
                .list(qEntityA);



        */





    }

}
