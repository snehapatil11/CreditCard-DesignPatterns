

import java.io.IOException;
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

    public void writeFile(String filename, List<List<String>> creditCardRecords ) throws IOException {
        fileParser.writeFile(filename,creditCardRecords);
    }

    public  List<List<String>> verifyCreateCreditCardRecord(List<List<String>> creditCardRecords){
        List<List<String>> recordswithTypeError = new ArrayList<List<String>>();
        String type,error = null;
        boolean numeric = true;
        CCVerificationHandler h1 = new MasterCardVerificationHandler();
        CCVerificationHandler h2 = new VisaVerificationHandler();
        CCVerificationHandler h3 = new DiscoverVerificationHandler();
        CCVerificationHandler h4 = new AmExVerificationHandler();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
        h3.setSuccessor(h4);
        for (int i = 0; i < creditCardRecords.size(); i++) {
            numeric = true;
            List<String> record = creditCardRecords.get(i);
            //String type = h1.verifyCreditCard(Long.parseLong(record.get(0)));
            try {
                Double num = Double.parseDouble(record.get(0));
            } catch (NumberFormatException e) {
                numeric = false;
            }

            if(record.get(0).length() > 19 || !numeric){
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
        CreditCardFactory factory = new CreditCardFactory();
        CreditCard card;
        for (int i = 0; i < records.size(); i++) {
            List<String> record = records.get(i);
            if(record.get(3) != "Invalid"){
                    card = factory.getCreditCard(record);
                    creditCards.add(card);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        CreditCardRecordReader recordReader = null;
        if (args.length != 2) {
            System.err.println("Not enough arguments! Please provide input and output filenames");
            return;
        }

        String extension = args[0].substring(args[0].lastIndexOf(".") + 1);
        String outputExtension = args[1].substring(args[1].lastIndexOf(".") + 1);

        if (extension.equals("csv") && outputExtension.equals("csv"))
            recordReader = new CreditCardRecordReader(new CSVFileParser());
        else if (extension.equals("json") && outputExtension.equals("json"))
            recordReader = new CreditCardRecordReader(new JSONFileParser());
        else if (extension.equals("xml") && outputExtension.equals("xml"))
            recordReader = new CreditCardRecordReader(new XMLFileParser());
        else{
            System.err.println("Please provide either csv, json or xml input & output files");
            return;
        }

        if(recordReader != null) {
            List<List<String>> creditCardRecords = recordReader.parsefile(args[0]);
            creditCardRecords = recordReader.verifyCreateCreditCardRecord(creditCardRecords);

            //All valid credit card objects are created.
            recordReader.createCreditCards(creditCardRecords);
            //System.out.println(recordReader.creditCards);

            recordReader.writeFile(args[1], creditCardRecords);
        }

    }
}
