

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public abstract class FileParser {
    public abstract List<List<String>> parseFile(String filename) ;
    public abstract void writeFile(String filename,List<List<String>> creditCardRecords ) throws IOException;
}
