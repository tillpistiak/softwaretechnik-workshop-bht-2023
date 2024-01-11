package de.bht.azur.model;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Appointment extends PanacheEntity {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private LocalDateTime start;
    @Getter
    @Setter
    private LocalDateTime end;

}
