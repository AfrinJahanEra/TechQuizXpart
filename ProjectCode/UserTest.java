package ProjectCode;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testUserCreation() {
        Subject subject = Subject.OOP;
        User user = new User("Afrin Jahan Era", "IUT", "CSE", subject);

        assertEquals("Afrin Jahan Era", user.getName());
        assertEquals("IUT", user.getUniversity());
        assertEquals("CSE", user.getDepartment());
        assertEquals(subject, user.getSubject());
    }
}

