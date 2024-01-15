package de.bht.azur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.bht.azur.service.UserService;
import de.bht.azur.service.AppointmentService;
import de.bht.azur.model.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private User user;

    @Mock
    private Appointment appointment;

    @Mock
    private AppointmentUser appointmentUser;

    @Mock
    private Group group;

    @Mock
    private GroupUser groupUser;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testGetAllUsers() {
//        userService.getAllUsers();
//        verify(User, times(1)).listAll();
//    }
//
//    @Test
//    public void testAddNewUser() {
//        userService.addNewUser(user);
//        verify(user, times(1)).persist();
//    }
//
//    @Test
//    public void testFindSingleUser() {
//        Long userId = 1L;
//        userService.findSingleUser(userId);
//        verify(User, times(1)).findById(userId);
//    }
//
//    @Test
//    public void testGetAppointmentsForUser() {
//        Long userId = 1L;
//        userService.getAppointmentsForUser(userId);
//        verify(AppointmentUser, times(1)).findByUserId(userId);
//    }
//
//    @Test
//    public void testUpdateUser() {
//        Long userId = 1L;
//        userService.updateUser(userId, user);
//        verify(user, times(1)).getEmail();
//        verify(user, times(1)).getFamilyName();
//        verify(user, times(1)).getGivenName();
//        verify(user, times(1)).persist();
//    }
//
//    @Test
//    public void testDeleteUser() {
//        Long userId = 1L;
//        userService.deleteUser(userId);
//        verify(User, times(1)).deleteById(userId);
//    }
//
//    @Test
//    public void testRemoveAppointment() {
//        Long userId = 1L;
//        Long appointmentId = 1L;
//        userService.removeAppointment(userId, appointmentId);
//        verify(AppointmentUser, times(1)).removeAppointmentForUser(userId, appointmentId);
//    }
//
//    @Test
//    public void testInviteUserToAppointment() {
//        Long userId = 1L;
//        Long appointmentId = 1L;
//        when(userService.findSingleUser(userId)).thenReturn(user);
//        when(appointmentService.findSingleAppointment(appointmentId)).thenReturn(appointment);
//        userService.inviteUserToAppointment(userId, appointmentId);
//        verify(appointmentUser, times(1)).persist();
//    }
//
//    @Test
//    public void testUpdateAppointmentStatus() {
//        Long userId = 1L;
//        Long appointmentId = 1L;
//        AppointmentStatus status = AppointmentStatus.INVITED;
//        when(AppointmentUser.findAppointmentUser(userId, appointmentId)).thenReturn(appointmentUser);
//        userService.updateAppointmentStatus(userId, appointmentId, status);
//        verify(appointmentUser, times(1)).setStatus(status);
//        verify(appointmentUser, times(1)).persist();
//    }
//
//    @Test
//    public void testUpdateGroupStatus() {
//        Long userId = 1L;
//        Long groupId = 1L;
//        GroupStatus status = GroupStatus.INVITED;
//        when(GroupUser.findGroupUser(userId, groupId)).thenReturn(groupUser);
//        userService.updateGroupStatus(userId, groupId, status);
//        verify(groupUser, times(1)).setStatus(status);
//        verify(groupUser, times(1)).persist();
//    }
//
//    @Test
//    public void testGetGroupsForUser() {
//        Long userId = 1L;
//        userService.getGroupsForUser(userId);
//        verify(GroupUser, times(1)).findByUserId(userId);
//    }
//
//    @Test
//    public void testRemoveGroup() {
//        Long userId = 1L;
//        Long groupId = 1L;
//        userService.removeGroup(userId, groupId);
//        verify(GroupUser, times(1)).removeGroupForUser(userId, groupId);
//    }
//
//    @Test
//    public void testInviteUserToGroup() {
//        Long userId = 1L;
//        Long groupId = 1L;
//        when(userService.findSingleUser(userId)).thenReturn(user);
//        when(Group.findById(groupId)).thenReturn(group);
//        userService.inviteUserToGroup(userId, groupId);
//        verify(groupUser, times(1)).persist();
//    }
}