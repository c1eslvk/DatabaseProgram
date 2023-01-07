import java.util.ArrayList;
import java.util.Map;

public class Table {
    String name;
    ArrayList<String> colNames;
    ArrayList<Map<String, String>> rows;

    public Table(String name, ArrayList<String> colNames) {
        this.name = name;
        this.colNames = colNames;
    }
}
