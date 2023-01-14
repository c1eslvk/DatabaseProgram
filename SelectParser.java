import java.util.ArrayList;

public class SelectParser {
    public void selectParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException {
        if (command[1].equalsIgnoreCase("*")) {
            selectAllParser(command, database);
        } else {
            selectSpecificParser(command, database);
        }
    }

    private void selectAllParser(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException {
        if (command[2].equalsIgnoreCase("from")) {
            String tableName = command[3];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                table.selectAll();
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }

    private void selectSpecificParser(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command[command.length - 2].equalsIgnoreCase("from")) {
            String tableName = command[command.length - 1];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                if (command.length - 3 <= table.colNames.size()) {
                    ArrayList<String> cols = new ArrayList<>();
                    for (int i = 1; i <= command.length - 3; i++) {
                        cols.add(command[i].replace(",", ""));
                    }
                    table.selectSpecific(cols);
                } else {
                    throw new InvalidSyntaxException("Invalid Syntax");
                }

            } else {
                throw new TableNotFoundException("Table not found.");
            }
        }
    }
}
