

import org.junit.Test;

import static org.junit.Assert.*;

public class AmExVerificationHandlerTest {

    @Test
    public void verifyInvalidCreditCard() {
        AmExVerificationHandler h1 = new AmExVerificationHandler();
        String type = h1.verifyCreditCard("5410000000000000");
        assertEquals(type, "Invalid");
    }
    @Test
    public void verifyCreditCard() {
        AmExVerificationHandler h1 = new AmExVerificationHandler();
        String type = h1.verifyCreditCard("341000000000000");
        assertEquals(type, "AmericanExpress");
    }


}