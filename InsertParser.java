import java.util.ArrayList;
import java.util.List;

public class InsertParser {
    public void insertParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command.length > 4 && command[1].equalsIgnoreCase("into") && command[3].equalsIgnoreCase("values")) {
            String tableName = command[2];
            if (database.containsTable(tableName)) {
                List<String> values = new ArrayList<>();
                for (int i = 4; i < command.length; i++) {
                    String value = command[i];
                    value = value.replace("(", "");
                    value = value.replace(",", "");
                    value = value.replace(")", "");
                    values.add(value);
                }
                Table table = database.getTable(tableName);
                if (values.size() == table.colNames.size()) {
                    table.insert(values);
                    table.saveTable();
                } else {
                    System.out.println("Not enough arguments.");
                }
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}
