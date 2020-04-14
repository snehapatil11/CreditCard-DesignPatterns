package creditpackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFileParser extends FileParser{
    @Override
    public List<List<String>> parseFile(String filename) {

        List<List<String>> jsonRecords = new ArrayList<List<String>>();
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filename))
        {
            //Read JSON file
            Object obj = parser.parse(reader);
            JSONArray jRecords = (JSONArray) obj;
            //System.out.println(jsonRecords);
            jRecords.forEach( record -> {
                //System.out.println(record);
                ArrayList<String> jsonRecord = new ArrayList<String>();
                JSONObject recordObject = (JSONObject) record;
                Long cardnumber = (Long) recordObject.get("CardNumber");
                jsonRecord.add(String.valueOf(cardnumber));
                String expiry = (String) recordObject.get("ExpirationDate");
                jsonRecord.add(expiry);
                String cardholder = (String) recordObject.get("NameOfCardholder");
                jsonRecord.add(cardholder);
                jsonRecords.add(jsonRecord);
            } );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonRecords;
    }

    @Override
    public void writeFile(String filename, List<List<String>> creditCardRecords) {
        JSONArray creditCardList = new JSONArray();
        creditCardRecords.forEach( jsonrecord -> {
            JSONObject record = new JSONObject();
            record.put("CardNumber", jsonrecord.get(0));
            record.put("Type", jsonrecord.get(3));
            record.put("Error", jsonrecord.get(4));
            creditCardList.add(record);
        });
        try (FileWriter file = new FileWriter(filename)) {

            file.write(creditCardList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
