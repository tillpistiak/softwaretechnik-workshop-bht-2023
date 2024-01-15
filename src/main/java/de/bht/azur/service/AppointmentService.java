package de.bht.azur.service;

import de.bht.azur.model.Appointment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class AppointmentService {

    @Transactional
    public Appointment findSingleAppointment(Long appointmentId) {
         Appointment appointment = Appointment.findById(appointmentId);
         if (appointment == null) {
             throw new NotFoundException();
         }
         return appointment;
    }

    @Transactional
    public List<Appointment> getAllAppointments() {
        return Appointment.listAll();
    }

    @Transactional
    public void addNewAppointment(Appointment newAppointment) {
        newAppointment.persist();
    }

    @Transactional
    public Appointment updateAppointment(Long appointmentId, Appointment appointment) {
        Appointment existingAppointment = findSingleAppointment(appointmentId);
        existingAppointment.setTitle(appointment.getTitle());
        existingAppointment.setDescription(appointment.getDescription());
        existingAppointment.setStart(appointment.getStart());
        existingAppointment.setEnd(appointment.getEnd());
        existingAppointment.persist();
        return existingAppointment;
    }

    @Transactional
    public void deleteAppointment(Long appointmentId) {
        Appointment.deleteById(appointmentId);
    }
}
