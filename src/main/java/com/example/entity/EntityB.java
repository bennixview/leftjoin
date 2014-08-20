package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_TEST_ENTITY_B")
public class EntityB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEST_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "TEST_ENTITY_A")
    private EntityA entityA;
    @ManyToOne
    @JoinColumn(name = "TEST_ENTITY_C")
    private EntityC entityC;
    public Long getId() {
        return id;
    }
    public EntityA getEntityA() {
        return entityA;
    }
    public void setEntityA(EntityA entityA) {
        this.entityA = entityA;
    }
    public EntityC getEntityC() {
        return entityC;
    }
    public void setEntityC(EntityC entityC) {
        this.entityC = entityC;
    }
}
