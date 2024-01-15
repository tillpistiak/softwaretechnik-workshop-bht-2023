package de.bht.azur.controller;

import de.bht.azur.model.*;
import de.bht.azur.service.AppointmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Path("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewAppointment(Appointment newAppointment) {
        appointmentService.addNewAppointment(newAppointment);
        return Response.accepted(newAppointment).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointment(@PathParam("id") Long appointmentId) {
        return appointmentService.findSingleAppointment(appointmentId);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") Long appointmentId, Appointment appointment) {
        Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, appointment);
        return Response.accepted(updatedAppointment).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAppointment(@PathParam("id") Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return Response.noContent().build();
    }
}