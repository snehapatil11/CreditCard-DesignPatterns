package creditpackage;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileParser extends FileParser{

    @Override
    public List<List<String>> parseFile(String filename) {
        List<List<String>> xmlRecords = new ArrayList<List<String>>();
        File xmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            //NodeList nodeList = doc.getElementsByTagName("Employee");
            //now XML is loaded as Document in memory, lets convert it to Object List
            /*List<Employee> empList = new ArrayList<Employee>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                empList.add(getEmployee(nodeList.item(i)));
            }
            //lets print Employee list information
            for (Employee emp : empList) {
                System.out.println(emp.toString());
            }*/
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        }
        xmlRecords.add(new ArrayList<String>() {{
            add("item1");
            add("item2");
            add("item3");
        }});
        return xmlRecords;
    }

    @Override
    public void writeFile() {

    }
}
