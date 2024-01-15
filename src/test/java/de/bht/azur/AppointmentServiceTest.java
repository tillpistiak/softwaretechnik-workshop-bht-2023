package de.bht.azur;

import de.bht.azur.model.Appointment;
import de.bht.azur.service.AppointmentService;
import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.quarkus.test.junit.QuarkusTest;

public class AppointmentServiceTest {

    @InjectMock
    AppointmentService appointmentService;

    @Test
    public void testFindSingleAppointment() {
        Long appointmentId = 1L;

        Appointment mockAppointment = mock(Appointment.class);
        when(Appointment.findById(appointmentId)).thenReturn(mockAppointment);
        mockStatic(Appointment.class).when(Appointment.findById(appointmentId)).thenReturn(mockAppointment);

        Appointment result = appointmentService.findSingleAppointment(appointmentId);
        assertEquals(mockAppointment, result);
    }
}