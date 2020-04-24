import java.util.List;

public class CreditCardFactory {

    //to get the credit card of specific type
    public CreditCard getCreditCard(List<String> record){
        CreditCard card = null;
        if(record.get(3) == "Visa"){
            card = new VisaCC(record.get(0), record.get(1),record.get(2), record.get(3));
        }
        if(record.get(3) == "MasterCard"){
            card = new MasterCardCC(record.get(0), record.get(1),record.get(2), record.get(3));
        }
        if(record.get(3) == "Discover"){
            card = new DiscoverCC(record.get(0), record.get(1),record.get(2), record.get(3));
        }
        if(record.get(3) == "AmericanExpress"){
            card = new AmericanExpressCC(record.get(0), record.get(1),record.get(2), record.get(3));
        }
        return card;
    }
}
