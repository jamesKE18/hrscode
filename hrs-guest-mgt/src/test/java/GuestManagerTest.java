import org.example.guestmgt.dao.GuestManager;
import org.example.guestmgt.entity.Guest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing GuestManager since most logic is completed in this class,
 * edge cases like empty strings, null inputs are not considered since data validation would be handled
 * in controller layer.
 * <p>
 * Ideally could write more unit tests about controller layer:
 * input validation, HTTP response codes in various situations
 */
public class GuestManagerTest {
    private GuestManager guestManager;

    @BeforeEach
    public void setUp() {
        // initialize a guest manager with 1 guest called James
        guestManager = new GuestManager();
        Guest testGuest = new Guest();
        testGuest.setName("James");
        testGuest.setCheckInTime(LocalDateTime.now().minusMinutes(10L));
        testGuest.setCheckOutTimeEst(LocalDateTime.now().plusDays(2L));
        guestManager.checkIn(testGuest);
    }

    @Test
    public void checkInNullGuest() {
        assertThrows(IllegalArgumentException.class, () -> guestManager.checkIn(null));
    }

    @Test
    public void normalCheckInTest() {
        // initial guest
        assertEquals(1, guestManager.listInHouseGuests(null).size());

        // checking in new guest
        Guest testGuest = new Guest();
        testGuest.setName("Jack");
        testGuest.setCheckInTime(LocalDateTime.now());
        guestManager.checkIn(testGuest);
        assertEquals(2, guestManager.listInHouseGuests(null).size());
    }

    @Test
    public void checkOutTest() {
        // initial guests
        assertEquals(1, guestManager.listInHouseGuests(null).size());
        assertEquals("James", guestManager.listInHouseGuests("James").get(0).getName());

        // checkout existing guest
        assertTrue(guestManager.checkOut("James"));
        assertTrue(guestManager.listInHouseGuests(null).isEmpty());
        // checkout non-existing guest
        assertFalse(guestManager.checkOut("Not existing guest"));
        assertTrue(guestManager.listInHouseGuests(null).isEmpty());
    }

    @Test
    public void listGuestTest() {
        // initial guests
        assertEquals(1, guestManager.listInHouseGuests(null).size());

        // add a 2nd guest
        Guest testGuest = new Guest();
        testGuest.setName("Jack");
        testGuest.setCheckInTime(LocalDateTime.now());
        guestManager.checkIn(testGuest);
        assertEquals(2, guestManager.listInHouseGuests(null).size());

        // list specified guest
        assertEquals(1, guestManager.listInHouseGuests("James").size());
        assertEquals("James", guestManager.listInHouseGuests("James").get(0).getName());
        // list non-existing guest
        assertTrue(guestManager.listInHouseGuests("Non existing guest").isEmpty());

    }

    @Test
    public void isGuestInHouseTest() {
        assertTrue(guestManager.isGuestInHouse("James"));
        assertFalse(guestManager.isGuestInHouse("Jackie"));
        assertFalse(guestManager.isGuestInHouse(""));
        assertFalse(guestManager.isGuestInHouse(null));
    }
}
