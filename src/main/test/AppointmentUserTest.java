import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
public class AppointmentUserTest {

    @Test
    public void testFindByUserId() {
        // Create test data
        AppointmentUser user1 = new AppointmentUser();
        user1.setId(1L);
        user1.setUserId(1L);
        user1.setName("Max");
        user1.setfamilyName("Mustermann");
        // Add user1 to the database

        AppointmentUser user2 = new AppointmentUser();
        user2.setId(2L);
        user2.setUserId(2L);
        user2.setName("Max");
        user2.setfamilyName("Schumeltgerne");
        // Add user2 to the database

        AppointmentUser user3 = new AppointmentUser();
        user3.setId(3L);
        user3.setUserId(1L);
        user3.setName("Maxina");
        user3.setfamilyName("Musterfrau");
        // Add user3 to the database

        // Call the method to be tested
        List<AppointmentUser> result = AppointmentUser.findByUserId(1L);

        // Assert the expected result
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertFalse(result.contains(user2));
        assertTrue(result.contains(user3));
    }

    // Other test methods...

}


