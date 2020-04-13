package creditpackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser extends FileParser{

    @Override
    public List<List<String>> parseFile(String filename) {
        List<List<String>> csvRecords = new ArrayList<List<String>>();
        String line = "";
        String cvsSplitBy = ",";
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {
                ArrayList<String> csvRecord = new ArrayList<String>();
                // use comma as separator
                String[] record = line.split(cvsSplitBy);

                if(i != 0) {
                    //System.out.println(Double.parseDouble(record[0]));
                    csvRecord.add(String.valueOf(new BigDecimal(record[0]).toBigInteger()));
                    csvRecord.add(record[1]);
                    csvRecord.add(record[2]);

                    csvRecords.add(csvRecord);
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvRecords;
    }

    @Override
    public void writeFile() {
        System.out.println("CSV Write");
    }
}
