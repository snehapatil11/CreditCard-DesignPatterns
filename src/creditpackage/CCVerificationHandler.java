package creditpackage;

public interface CCVerificationHandler {
    public String verifyCreditCard(String creditCardNumber);
    public void setSuccessor(CCVerificationHandler next);
}
