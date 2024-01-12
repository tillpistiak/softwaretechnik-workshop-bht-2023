package de.bht.azur.controller;

import de.bht.azur.model.AppointmentStatus;
import de.bht.azur.model.AppointmentUser;
import de.bht.azur.model.User;
import de.bht.azur.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(User newUser) {
        userService.addNewUser(newUser);
        return Response.accepted(newUser).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long userId) {
        return userService.findSingleUser(userId);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long userId, User user) {
        User updatedUser = userService.updateUser(userId, user);
        return Response.accepted(updatedUser).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Long userId) {
        userService.deleteUser(userId);
        return Response.noContent().build();
    }

    @Path("/{id}/appointments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AppointmentUser> getAppointmentsForUser(@PathParam("id") Long userId) {
        return userService.getAppointmentsForUser(userId);
    }

    @Path("/{id}/appointments/{appid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsForUser(@PathParam("id") Long userId, @PathParam("appid") Long appointmentId) {
        userService.removeAppointment(userId, appointmentId);
        return Response.noContent().build();
    }

    @Path("/{id}/appointments/{appid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response inviteUserToAppointment(@PathParam("id") Long userId, @PathParam("appid") Long appointmentId) {
        AppointmentUser appointmentUser = userService.inviteUserToAppointment(userId, appointmentId);
        return Response.accepted(appointmentUser).build();
    }

    @Path("/{id}/appointments/{appid}/status/{status}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointmentStatus(@PathParam("id") Long userId, @PathParam("appid") Long appointmentId, @PathParam("status")AppointmentStatus status) {
        AppointmentUser appointmentUser = userService.updateAppointmentStatus(userId, appointmentId, status);
        return Response.accepted(appointmentUser).build();
    }
}
