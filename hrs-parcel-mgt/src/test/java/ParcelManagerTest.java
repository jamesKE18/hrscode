import org.example.parcelmgt.dao.ParcelManager;
import org.example.parcelmgt.entity.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParcelManagerTest {
    private ParcelManager parcelManager;

    @BeforeEach
    public void setUp() {
        parcelManager = new ParcelManager();
        Parcel testParcel = new Parcel();
        testParcel.setRecipient("James");
        testParcel.setContent("Phone");
        testParcel.setTimeReceived(LocalDateTime.now().minusMinutes(10L));
        parcelManager.addParcel(testParcel);
    }

    @Test
    public void addNullParcelTest() {
        assertThrows(IllegalArgumentException.class, () -> parcelManager.addParcel(null));
    }

    @Test
    public void addParcelTest() {
        // initial inventory, 1 parcel
        assertEquals(1, parcelManager.listParcel("James").size());

        // add 1 new parcel to james
        Parcel testParcel = new Parcel();
        testParcel.setRecipient("James");
        testParcel.setContent("Laptop");
        testParcel.setTimeReceived(LocalDateTime.now().minusMinutes(20L));
        parcelManager.addParcel(testParcel);
        assertEquals(2, parcelManager.listParcel("James").size());
        // preserves insert order
        assertEquals("Phone", parcelManager.listParcel("James").get(0).getContent());
        assertEquals("Laptop", parcelManager.listParcel("James").get(1).getContent());

        // parcel for jack, initially none
        assertTrue(parcelManager.listParcel("Jack").isEmpty());
        // add 1 new parcel to jack
        testParcel = new Parcel();
        testParcel.setRecipient("Jack");
        testParcel.setContent("iPad");
        testParcel.setTimeReceived(LocalDateTime.now().minusMinutes(20L));
        parcelManager.addParcel(testParcel);
        assertEquals(1, parcelManager.listParcel("Jack").size());
    }

    @Test
    public void collectParcelTest() {
        // initial inventory, 1 parcel
        assertEquals(1, parcelManager.listParcel("James").size());

        // collect the parcel
        assertEquals(1, parcelManager.collectParcel("James").size());
        assertTrue(parcelManager.listParcel("James").isEmpty());
    }
}
