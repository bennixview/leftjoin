package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "T_TEST_ENTITY_A")
public class EntityA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEST_ID")
    private Long id;

    @OneToMany(mappedBy = "entityA")
    private List<EntityB> entityBs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<EntityB> getEntityBs() {
        return entityBs;
    }

}
