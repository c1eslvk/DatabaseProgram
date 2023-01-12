import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private HashMap<String,Table> tables = new HashMap<>();
    private ArrayList<String> tableNames = new ArrayList<>();

    public void addTable(Table table) {
        table.saveTable();
        tables.put(table.name, table);
    }

    public void loadTables() {
        File folder = new File("database");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null && listOfFiles.length != 0) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    tableNames.add(file.getName());
                    tables.put(file.getName(), new Table(file.getName()));
                }
            }
        }
    }

    public boolean isEmpty() {
        return tables.isEmpty();
    }

    public void listTables() {
        System.out.println("Tables in database:");
        System.out.println("-------------------");
        int counter = 1;
        for (String table : tableNames) {
            System.out.println(counter + ". " + table);
            counter++;
        }
        System.out.println("-------------------");
    }

    public boolean containsTable(String name) {
        return tableNames.contains(name);
    }

    public Table getTable(String name) {
        return tables.get(name);
    }
}
