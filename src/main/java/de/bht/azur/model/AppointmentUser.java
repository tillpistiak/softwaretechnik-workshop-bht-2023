package de.bht.azur.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "appointment_user")
public class AppointmentUser extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    @Getter
    @Setter
    private Appointment appointment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private boolean owner;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    private AppointmentStatus status;
}
