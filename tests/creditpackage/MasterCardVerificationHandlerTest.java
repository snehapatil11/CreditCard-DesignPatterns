package creditpackage;

import org.junit.Test;

import static org.junit.Assert.*;

public class MasterCardVerificationHandlerTest {

    @Test
    public void verifyCreditCard() {
        MasterCardVerificationHandler h1 = new MasterCardVerificationHandler();
        String type = h1.verifyCreditCard("5410000000000000");
        assertEquals(type, "MasterCard");
    }
    @Test
    public void verifyInvalidCreditCard() {
        MasterCardVerificationHandler h1 = new MasterCardVerificationHandler();
        String type = h1.verifyCreditCard("54000000000000");
        assertNull(type);
    }
}