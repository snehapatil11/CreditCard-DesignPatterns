

import org.junit.Test;

import static org.junit.Assert.*;

public class VisaVerificationHandlerTest {

    @Test
    public void verifyCreditCard() {
        VisaVerificationHandler h1 = new VisaVerificationHandler();
        String type = h1.verifyCreditCard("4120000000000");
        assertEquals(type, "Visa");
    }
    @Test
    public void verifyInvalidCreditCard() {
        VisaVerificationHandler h1 = new VisaVerificationHandler();
        String type = h1.verifyCreditCard("41200000000");
        assertEquals(type, "Invalid");
    }
}