import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupUserTest {

    @Test
    public void testAddUserToGroup() {
        // Arrange
        Group group = new Group();
        User user = new User("John");

        // Act
        group.addUser(user);

        // Assert
        Assertions.assertTrue(group.hasUser(user));
    }

    @Test
    public void testRemoveUserFromGroup() {
        // Arrange
        Group group = new Group();
        User user = new User("John");
        group.addUser(user);

        // Act
        group.removeUser(user);

        // Assert
        Assertions.assertFalse(group.hasUser(user));
    }
}
