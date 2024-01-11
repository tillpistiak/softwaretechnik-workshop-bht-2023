package de.bht.azur.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class User extends PanacheEntity {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

}
