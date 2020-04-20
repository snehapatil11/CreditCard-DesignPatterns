

public abstract class CreditCard {
    private Long creditCardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String cardType;

    public CreditCard(Long number, String date, String holderName, String type){
        creditCardNumber = number;
        expirationDate = date;
        cardHolderName = holderName;
        cardType = type;
    }
}
