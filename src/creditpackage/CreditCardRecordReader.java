package creditpackage;

import java.util.ArrayList;
import java.util.List;

public class CreditCardRecordReader {
    private FileParser fileParser;
    private List<CreditCard> creditCards;

    public CreditCardRecordReader(FileParser fileParser1){
        fileParser = fileParser1;
        creditCards = new ArrayList<CreditCard>();
    }
    public List<List<String>> parsefile(String filename){
        System.out.println(filename);
        return fileParser.parseFile(filename);
    }
    public  List<List<String>> verifyCreateCreditCardRecord(List<List<String>> creditCardRecords){
        List<List<String>> recordswithTypeError = new ArrayList<List<String>>();
        String type,error = null;
        CCVerificationHandler h1 = new MasterCardVerificationHandler();
        CCVerificationHandler h2 = new VisaVerificationHandler();
        CCVerificationHandler h3 = new DiscoverVerificationHandler();
        CCVerificationHandler h4 = new AmExVerificationHandler();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
        h3.setSuccessor(h4);
        for (int i = 0; i < creditCardRecords.size(); i++) {
            List<String> record = creditCardRecords.get(i);
            //String type = h1.verifyCreditCard(Long.parseLong(record.get(0)));
            if(record.get(0).length() > 19){
                type = "Invalid";
                error = "InvalidCardNumber";
            }
            else {
                type = h1.verifyCreditCard(record.get(0));
                if(type == "Invalid")
                    error = "InvalidCardNumber";
                else
                    error = "None";
            }
            record.add(type);
            record.add(error);
            recordswithTypeError.add(record);
        }
        return recordswithTypeError;
    }
    public void createCreditCards(List<List<String>> records){
        CreditCard card;
        for (int i = 0; i < records.size(); i++) {
            List<String> record = records.get(i);
            if(record.get(3) != "Invalid"){
                if(record.get(3) == "Visa"){
                    card = new VisaCC(Long.parseLong(record.get(0)), record.get(1),record.get(2), record.get(3));
                    creditCards.add(card);
                }
                if(record.get(3) == "MasterCard"){
                    card = new MasterCardCC(Long.parseLong(record.get(0)), record.get(1),record.get(2), record.get(3));
                    creditCards.add(card);
                }
                if(record.get(3) == "Discover"){
                    card = new DiscoverCC(Long.parseLong(record.get(0)), record.get(1),record.get(2), record.get(3));
                    creditCards.add(card);
                }
                if(record.get(3) == "AmericanExpress"){
                    card = new AmericanExpressCC(Long.parseLong(record.get(0)), record.get(1),record.get(2), record.get(3));
                    creditCards.add(card);
                }
            }
        }

    }
    public static void main(String[] args) {

        CreditCardRecordReader recordReader = null;
        String extension = args[0].substring(args[0].lastIndexOf(".") +1);

        if(extension.equals("csv"))
            recordReader = new CreditCardRecordReader(new CSVFileParser());
        else if(extension.equals("xml"))
            recordReader = new CreditCardRecordReader(new XMLFileParser());
        else if(extension.equals("json"))
            recordReader = new CreditCardRecordReader(new JSONFileParser());

        if(recordReader != null) {
            List<List<String>> creditCardRecords = recordReader.parsefile(args[0]);
            creditCardRecords = recordReader.verifyCreateCreditCardRecord(creditCardRecords);
            //System.out.println(creditCardRecords);
            recordReader.createCreditCards(creditCardRecords);
            System.out.println(recordReader.creditCards);
        }

    }
}
