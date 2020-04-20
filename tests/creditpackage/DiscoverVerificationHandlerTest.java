package creditpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscoverVerificationHandlerTest {

    @Test
    public void verifyCreditCard() {
        DiscoverVerificationHandler h1 = new DiscoverVerificationHandler();
        String type = h1.verifyCreditCard("6011000000000000");
        assertEquals(type, "Discover");
    }
    @Test
    public void verifyInvalidCreditCard() {
        DiscoverVerificationHandler h1 = new DiscoverVerificationHandler();
        String type = h1.verifyCreditCard("60100000000000");
        assertNull(type);
    }

}