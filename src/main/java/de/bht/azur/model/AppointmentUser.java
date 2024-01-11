package de.bht.azur.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "appointment_user")
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUser extends PanacheEntity {
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
    private AppointmentStatus status;
}
