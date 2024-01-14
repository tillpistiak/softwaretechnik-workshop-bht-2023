package de.bht.azur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.User;

public class AppointmentTest {

    @BeforeEach
    void setUp() {
        // Set up any necessary test data or mocks
    }

    @Test
    void testAppointmentCreation() {
        // Test appointment creation logic
    }

    @Test
    void testAppointmentId() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        assertEquals(1L, appointment.getId());
    }

    @Test
    void testAppointmentTitle() {
        Appointment appointment = new Appointment();
        appointment.setTitle("Test");

        assertEquals("Test", appointment.getTitle());
    }
    
}
