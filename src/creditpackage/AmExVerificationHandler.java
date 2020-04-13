package creditpackage;

public class AmExVerificationHandler implements CCVerificationHandler {
    private CCVerificationHandler successor = null;
    @Override
    public String verifyCreditCard(String creditCardNumber) {
        String type = "Invalid";
        if(creditCardNumber.length() == 15 && creditCardNumber.charAt(0) == '3' && (creditCardNumber.charAt(1) =='4' || creditCardNumber.charAt(1) == '7'))
        {
            type= "AmericanExpress";
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
