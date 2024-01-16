package de.bht.azur;


import de.bht.azur.model.*;
import jakarta.transaction.Transactional;

public class ParentTest {

    @Transactional
    void cleanup() {
        AppointmentUser.deleteAll();
        GroupUser.deleteAll();
        User.deleteAll();
        Appointment.deleteAll();
        Group.deleteAll();
    }
}
