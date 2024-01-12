package de.bht.azur.service;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.AppointmentStatus;
import de.bht.azur.model.AppointmentUser;
import de.bht.azur.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class UserService {

    private final AppointmentService appointmentService;

    @Transactional
    public List<User> getAllUsers() {
        return User.listAll();
    }

    @Transactional
    public void addNewUser(User newUser) {
        newUser.persist();
    }

    @Transactional
    public User findSingleUser(Long userId) {
        return User.findById(userId);
    }

    @Transactional
    public List<AppointmentUser> getAppointmentsForUser(Long userId) {
        return AppointmentUser.findByUserId(userId);
    }

    @Transactional
    public User updateUser(Long userId, User user) {
        User existingUser = findSingleUser(userId);
        existingUser.setEmail(user.getEmail());
        existingUser.setFamilyName(user.getFamilyName());
        existingUser.setGivenName(user.getGivenName());
        existingUser.persist();
        return existingUser;
    }

    @Transactional
    public void deleteUser(Long userId) {
        User.deleteById(userId);
    }

    @Transactional
    public void removeAppointment(Long userId, Long appointmentId) {
        AppointmentUser.removeAppointmentForUser(userId, appointmentId);
    }

    @Transactional
    public AppointmentUser inviteUserToAppointment(Long userId, Long appointmentId) {
        User user = findSingleUser(userId);
        Appointment appointment = appointmentService.findSingleAppointment(appointmentId);
        AppointmentUser appointmentUser = AppointmentUser.builder()
                .user(user)
                .appointment(appointment)
                .owner(false)
                .status(AppointmentStatus.INVITED)
                .build();
        appointmentUser.persist();
        return appointmentUser;
    }

    @Transactional
    public AppointmentUser updateAppointmentStatus(Long userId, Long appointmentId, AppointmentStatus status) {
        AppointmentUser appointmentUser = AppointmentUser.findAppointmentUser(userId, appointmentId);
        appointmentUser.setStatus(status);
        appointmentUser.persist();
        return appointmentUser;
    }
}
