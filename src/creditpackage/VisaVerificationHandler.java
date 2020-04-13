package creditpackage;

public class VisaVerificationHandler implements CCVerificationHandler {
    private CCVerificationHandler successor = null;
    @Override
    public String verifyCreditCard(String creditCardNumber) {
        String type = null;
        if((creditCardNumber.length() == 13 || creditCardNumber.length() == 16 )&& creditCardNumber.charAt(0) == '4')
        {
            type= "Visa";
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
