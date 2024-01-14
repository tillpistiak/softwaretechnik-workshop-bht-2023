package de.bht.azur.controller;

import de.bht.azur.model.*;
import de.bht.azur.service.GroupService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.stream.Collectors;

@Path("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewGroup(Group newGroup) {
        groupService.addNewGroup(newGroup);
        return Response.accepted(newGroup).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Group getGroup(@PathParam("id") Long groupId) {
        return groupService.findSingleGroup(groupId);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGroup(@PathParam("id") Long groupId, Group group) {
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return Response.accepted(updatedGroup).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGroup(@PathParam("id") Long groupId) {
        groupService.deleteGroup(groupId);
        return Response.noContent().build();
    }

}