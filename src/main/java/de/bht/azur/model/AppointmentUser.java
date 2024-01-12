package de.bht.azur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.arc.All;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "appointment_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private User user;
    @Getter
    @Setter
    private boolean owner;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    private AppointmentStatus status;

    public static List<AppointmentUser> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public static void removeAppointmentForUser(Long userId, Long appointmentId) {
        AppointmentUser.delete("user.id = ?1 and appointment.id = ?2", userId, appointmentId);
    }

    public static AppointmentUser findAppointmentUser(Long userId, Long appointmentId) {
        return AppointmentUser
                .find("user.id = ?1 and appointment.id = ?2", userId, appointmentId)
                .firstResult();
    }
}
