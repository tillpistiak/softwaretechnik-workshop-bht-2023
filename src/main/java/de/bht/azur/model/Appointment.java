package de.bht.azur.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends PanacheEntity {
    @OneToMany(mappedBy = "appointment")
    @Getter
    @Setter
    private List<AppointmentUser> users = new ArrayList<>();
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
