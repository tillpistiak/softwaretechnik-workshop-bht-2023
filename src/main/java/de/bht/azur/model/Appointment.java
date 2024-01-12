package de.bht.azur.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "appointment")
public class Appointment extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JsonIgnore
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
