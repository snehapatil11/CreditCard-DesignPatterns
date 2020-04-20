package creditpackage;

public class DiscoverVerificationHandler implements CCVerificationHandler {
    private CCVerificationHandler successor = null;
    @Override
    public String verifyCreditCard(String creditCardNumber) {
        String type = null;
        if(creditCardNumber.length() == 16 && creditCardNumber.startsWith("6011"))
        {
            type = "Discover";
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
