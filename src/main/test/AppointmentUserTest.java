import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;
import org.mockito.Mockito;
import de.bht.azur.service.AppointmentUser;
import de.bht.azur.service.AppointmentUserService; // Stellen Sie sicher, dass Sie diesen Import haben

public class AppointmentUserTest {

    @Test
    public void testFindByUserId() {
        // Create test data
        AppointmentUser user1 = new AppointmentUser();
        user1.setId(1L);
        user1.setUserId(1L);
        user1.setName("Max");
        user1.setfamilyName("Mustermann");

        AppointmentUser user2 = new AppointmentUser();
        user2.setId(2L);
        user2.setUserId(2L);
        user2.setName("Max");
        user2.setfamilyName("Schumeltgerne");

        AppointmentUser user3 = new AppointmentUser();
        user3.setId(3L);
        user3.setUserId(1L);
        user3.setName("Maxina");
        user3.setfamilyName("Musterfrau");

        // Mock the findByUserId method
        AppointmentUserService mockService = Mockito.mock(AppointmentUserService.class);
        Mockito.when(mockService.findByUserId(1L)).thenReturn(Arrays.asList(user1, user3));

        // Call the method to be tested
        List<AppointmentUser> result = mockService.findByUserId(1L);

        // Verify the results
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user3));
    }
}