package creditpackage;

public class MasterCardVerificationHandler implements CCVerificationHandler {
    private CCVerificationHandler successor = null;
    @Override
    public String verifyCreditCard(String creditCardNumber) {
        String type = null;
        if(creditCardNumber.length() == 16 && creditCardNumber.charAt(0) == '5')
        {
            //if((creditCardNumber.charAt(1) - '0') <= '5' && creditCardNumber.charAt(1)>= 1)
            int SecDigit = creditCardNumber.charAt(1) - '0';
            if(SecDigit <= 5 && SecDigit >=1)
                type = "MasterCard";
        }
        else if(successor != null)
            type = successor.verifyCreditCard(creditCardNumber);
        return type;
    }

    @Override
    public void setSuccessor(CCVerificationHandler next) {
        this.successor = next;
    }
}
