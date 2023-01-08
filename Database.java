import java.util.HashMap;

public class Database {
    private HashMap<String,Table> tables = new HashMap<>();

    public void addTable(Table table) {
        table.saveTable();
        tables.put(table.name, table);
    }

    public void loadTables() {
        // code
    }

    public boolean isEmpty() {
        return tables.isEmpty();
    }
}
