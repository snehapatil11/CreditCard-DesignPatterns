package creditpackage;

import java.util.List;

public abstract class FileParser {
    public abstract List<List<String>> parseFile(String filename);
    public abstract void writeFile();
}
