package de.bht.azur.service;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.AppointmentStatus;
import de.bht.azur.model.AppointmentUser;
import de.bht.azur.model.Group;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class GroupService {
    private final AppointmentService appointmentService;
    @Transactional
    public Group findSingleGroup(Long groupId) {
        Group group = Group.findById(groupId);
        if(group == null) {
            throw new NotFoundException();
        }
        return group;
    }

    @Transactional
    public List<Group> getAllGroups() {
        return Group.listAll();
    }

    @Transactional
    public void addNewGroup(Group newGroup) {
        newGroup.persist();
    }

    @Transactional
    public Group updateGroup(Long groupId, Group group) {
        Group existingGroup = findSingleGroup(groupId);
        existingGroup.setName(group.getName());
        existingGroup.persist();
        return existingGroup;
    }

    @Transactional
    public void deleteGroup(Long groupId) {
        Group.deleteById(groupId);
    }

    @Transactional
    public List<AppointmentUser> inviteGroupToAppointment(Long groupId, Long appointmentId) {
        Group group = findSingleGroup(groupId);
        Appointment appointment = appointmentService.findSingleAppointment(appointmentId);
        return  group.getUsers()
                .stream()
                .map(user -> {
                    AppointmentUser appointmentUser = AppointmentUser.builder()
                            .user(user.getUser())
                            .appointment(appointment)
                            .owner(false)
                            .status(AppointmentStatus.INVITED)
                            .build();
                    appointmentUser.persist();
                    return appointmentUser;
                })
                .collect(Collectors.toList());
    }
}
