package creditpackage;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLFileParser extends FileParser{

    @Override
    public List<List<String>> parseFile(String filename) {
        List<List<String>> xmlRecords = new ArrayList<List<String>>();
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("row");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    ArrayList<String> xmlRecord = new ArrayList<String>();

                    Double val = Double.parseDouble(eElement.getElementsByTagName("CardNumber").item(0).getTextContent());
                    xmlRecord.add(String.valueOf(new BigDecimal(val).toBigInteger()));
                    xmlRecord.add(eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent());
                    xmlRecord.add(eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent());

                    xmlRecords.add(xmlRecord);
                }
            }
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        return xmlRecords;
    }

    @Override
    public void writeFile(String filename, List<List<String>> creditCardRecords) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            // root element
            Element root = document.createElement("root");
            document.appendChild(root);

            creditCardRecords.forEach( record -> {

                Element employee = document.createElement("row");
                root.appendChild(employee);

                Element cardNumber = document.createElement("CardNumber");
                cardNumber.appendChild(document.createTextNode(record.get(0)));
                employee.appendChild(cardNumber);
                Element cardType = document.createElement("CardType");
                cardType.appendChild(document.createTextNode(record.get(3)));
                employee.appendChild(cardType);
                Element error = document.createElement("Error");
                error.appendChild(document.createTextNode(record.get(4)));
                employee.appendChild(error);

            });
            //transform the DOM Object to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filename));


            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
