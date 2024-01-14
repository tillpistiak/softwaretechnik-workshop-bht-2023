package de.bht.azur.service;

import de.bht.azur.model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class AppointmentServiceTest {

    @InjectMock
    AppointmentService appointmentService;

    @Test
    public void testFindSingleAppointment() {
        Long appointmentId = 1L;

        Appointment mockAppointment = mock(Appointment.class);
        when(Appointment.findById(appointmentId)).thenReturn(mockAppointment);

        Appointment result = appointmentService.findSingleAppointment(appointmentId);
        assertEquals(mockAppointment, result);
    }
}