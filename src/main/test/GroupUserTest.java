package de.bht.azur.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import java.util.Arrays;
import java.util.List;

@QuarkusTest
public class GroupUserTest {

    @InjectMock
    GroupUser groupUser;

    @Test
    public void testFindGroupUser() {
        Long userId = 1L;
        Long groupId = 2L;

        GroupUser mockGroupUser = mock(GroupUser.class);
        when(mockGroupUser.findGroupUser(userId, groupId)).thenReturn(mockGroupUser);

        GroupUser result = groupUser.findGroupUser(userId, groupId);
        assertEquals(mockGroupUser, result);
    }

    @Test
    public void testFindByUserId() {
        Long userId = 1L;

        List<GroupUser> mockGroupUsers = Arrays.asList(mock(GroupUser.class));
        when(groupUser.findByUserId(userId)).thenReturn(mockGroupUsers);

        List<GroupUser> result = groupUser.findByUserId(userId);
        assertEquals(mockGroupUsers, result);
    }

    @Test
    public void testRemoveGroupForUser() {
        Long userId = 1L;
        Long groupId = 2L;

        doNothing().when(groupUser).removeGroupForUser(userId, groupId);

        groupUser.removeGroupForUser(userId, groupId);
        verify(groupUser, times(1)).removeGroupForUser(userId, groupId);
    }
}