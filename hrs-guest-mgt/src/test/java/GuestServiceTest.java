import org.example.guestmgt.dao.GuestManager;
import org.example.guestmgt.entity.GuestCheckOutResponse;
import org.example.guestmgt.entity.Parcel;
import org.example.guestmgt.service.GuestService;
import org.example.guestmgt.service.ParcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Testing checkOut method only since this has the most logic, for simplicity.
 * Also only testing normal cases since it would be tedious to cover all cases
 */
@ExtendWith(SpringExtension.class)
public class GuestServiceTest {

    private final GuestService guestService = new GuestService();

    @MockBean
    private ParcelService parcelService;

    @MockBean
    private GuestManager guestManager;

    @BeforeEach
    public void setUp() {
        guestService.setParcelService(parcelService);
        guestService.setGuestManager(guestManager);
        List<Parcel> mockParcels = new ArrayList<>(1);
        Parcel parcel = new Parcel();
        parcel.setRecipient("James");
        parcel.setContent("Phone");
        mockParcels.add(parcel);
        Mockito.when(parcelService.listParcel(eq("James"))).thenReturn(mockParcels);
        Mockito.when(guestManager.checkOut(any())).thenReturn(true);
    }

    @Test
    public void normalCaseTest() {
        GuestCheckOutResponse response = guestService.checkOut("James");
        assertTrue(response.isCheckedOut());
        assertEquals(1, response.getUncollectedParcels().size());
        assertEquals("Phone", response.getUncollectedParcels().get(0).getContent());
    }
}
