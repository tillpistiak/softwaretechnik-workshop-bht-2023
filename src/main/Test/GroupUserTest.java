import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroupUserTest {

    private GroupUser groupUser;

    @BeforeEach
    public void setUp() {
        groupUser = new GroupUser();
    }

    @Test
    public void testFindGroupUser() {
        // Arrange
        String userId = "user123";
        String groupId = "group456";
        groupUser.addGroupForUser(userId, groupId);

        // Act
        String foundGroupId = groupUser.findGroupUser(userId);

        // Assert
        Assertions.assertEquals(groupId, foundGroupId);
    }

    @Test
    public void testFindByUserId() {
        // Arrange
        String userId = "user123";
        String groupId = "group456";
        groupUser.addGroupForUser(userId, groupId);

        // Act
        boolean found = groupUser.findByUserId(userId);

        // Assert
        Assertions.assertTrue(found);
    }

    @Test
    public void testRemoveGroupForUser() {
        // Arrange
        String userId = "user123";
        String groupId = "group456";
        groupUser.addGroupForUser(userId, groupId);

        // Act
        groupUser.removeGroupForUser(userId);

        // Assert
        Assertions.assertFalse(groupUser.findByUserId(userId));
    }
}
