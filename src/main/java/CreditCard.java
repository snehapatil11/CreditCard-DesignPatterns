

public abstract class CreditCard {
    private String creditCardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String cardType;

    public CreditCard(String number, String date, String holderName, String type){
        this.creditCardNumber = number;
        this.expirationDate = date;
        this.cardHolderName = holderName;
        this.cardType = type;
    }
}
