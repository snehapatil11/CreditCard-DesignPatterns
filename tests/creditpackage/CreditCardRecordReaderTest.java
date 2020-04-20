package creditpackage;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CreditCardRecordReaderTest {

    @Test
    public void verifyCreateCreditCardRecord() {
        CreditCardRecordReader recordReader = new CreditCardRecordReader(new CSVFileParser());
        List<List<String>> records;
        List<List<String>> testrecord = new ArrayList<List<String>>();
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("60110000000000000000");
        list1.add("6/20/2030");
        list1.add("Richard");
        testrecord.add(list1);

        records = recordReader.verifyCreateCreditCardRecord(testrecord);
        List<String> record = records.get(0);
        assertEquals(record.get(3),"Invalid");
    }

    @Test
    public void verifyInvalidCreateCreditCardRecord() {
        CreditCardRecordReader recordReader = new CreditCardRecordReader(new CSVFileParser());
        List<List<String>> records;
        List<List<String>> testrecord = new ArrayList<List<String>>();
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("60110000000000000000");
        list1.add("6/20/2030");
        list1.add("Richard");
        testrecord.add(list1);

        records = recordReader.verifyCreateCreditCardRecord(testrecord);
        List<String> record = records.get(0);
        assertEquals(record.get(4),"InvalidCardNumber");
    }

    @Test
    public void verifyInvalidCreateCreditCardNumber() {
        CreditCardRecordReader recordReader = new CreditCardRecordReader(new CSVFileParser());
        List<List<String>> records;
        List<List<String>> testrecord = new ArrayList<List<String>>();
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("abcdef0000000000");
        list1.add("6/20/2030");
        list1.add("Richard");
        testrecord.add(list1);

        records = recordReader.verifyCreateCreditCardRecord(testrecord);
        List<String> record = records.get(0);
        assertEquals(record.get(4),"InvalidCardNumber");
    }
}