package de.bht.azur.service;

import de.bht.azur.model.Appointment;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppointmentService {
    public Appointment findSingleAppointment(Long appointmentId) {
        return Appointment.findById(appointmentId);
    }
}
