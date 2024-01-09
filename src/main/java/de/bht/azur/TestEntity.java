package de.bht.azur;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class TestEntity extends PanacheEntity {
    public String name;
}
