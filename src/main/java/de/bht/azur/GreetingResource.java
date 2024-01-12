package de.bht.azur;

import de.bht.azur.model.*;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.stream.Collectors;

@Path("/test")
public class GreetingResource {
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello World!";
    }

    @Path("/create")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public String create() {
        Appointment appointment = new Appointment();
        appointment.setDescription("Test Description");
        appointment.setTitle("Test Title");
        appointment.setStart(LocalDateTime.now());
        appointment.setEnd(LocalDateTime.now().plus(Duration.of(2, ChronoUnit.HOURS)));
        appointment.persist();

        User user = new User();
        user.setEmail("test@test.de");
        user.setGivenName("Max");
        user.setFamilyName("Mustermann");
        user.persist();

        Group group = new Group();
        group.setName("Mustermann GmbH");
        group.persist();

        AppointmentUser appointmentUser = new AppointmentUser();
        appointmentUser.setAppointment(appointment);
        appointmentUser.setUser(user);
        appointmentUser.setOwner(true);
        appointmentUser.setStatus(AppointmentStatus.ACCEPTED);
        appointmentUser.persist();

        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(group);
        groupUser.setUser(user);
        groupUser.setOwner(true);
        groupUser.setStatus(GroupStatus.JOINED);
        groupUser.persist();
        return "success";
    }
}
