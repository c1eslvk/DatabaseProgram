import java.util.ArrayList;
import java.util.List;

public class InsertParser {
    public void insertParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command.length > 4 && command[1].equalsIgnoreCase("into") && command[3].equalsIgnoreCase("values")) {
            String tableName = command[2];
            if (database.containsTable(tableName)) {
                List<String> values = new ArrayList<>();
                for (int i = 4; i < command.length; i++) {
                    if (command[i].endsWith(",")) {
                        if (i == 4 && command[i].startsWith("(")) {
                            values.add(command[i].replace(",", "").replace("(", ""));
                        } else if (i != 4) {
                            values.add(command[i].replace(",", ""));
                        } else {
                            throw new InvalidSyntaxException("Invalid Syntax");
                        }
                    } else {
                        if (command[i].endsWith(")")) {
                            values.add(command[i].replace(")", ""));
                            break;
                        } else {
                            throw new InvalidSyntaxException("Invalid Syntax");
                        }
                    }
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
