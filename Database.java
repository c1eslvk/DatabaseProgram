import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private Map<String,Table> tables = new HashMap<>();
    private List<String> tableNames = new ArrayList<>();

    public void addTable(Table table) {
        table.saveTable();
        tableNames.add(table.name);
        tables.put(table.name, table);
    }

    public void loadTables() {
        File folder = new File("database");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null && listOfFiles.length != 0) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    addTable(new Table(file.getName()));
//                    tableNames.add(file.getName());
//                    tables.put(file.getName(), new Table(file.getName()));
                }
            }
        }
    }

    public boolean isEmpty() {
        return tables.isEmpty();
    }

    public void listTables() {
        if (!isEmpty()) {
            System.out.println("Tables in database:");
            System.out.println("-------------------");
            int counter = 1;
            for (String table : tableNames) {
                System.out.println(counter + ". " + table);
                counter++;
            }
            System.out.println("-------------------");
        }
        else {
            System.out.println("Database is empty.");
        }
    }

    public boolean containsTable(String name) {
        return tableNames.contains(name);
    }

    public Table getTable(String name) {
        return tables.get(name);
    }
}
