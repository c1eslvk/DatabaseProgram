import java.util.ArrayList;
import java.util.List;

public class UpdateParser {
    public void updateParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command[2].equalsIgnoreCase("set")) {
            String tableName = command[1];
            List<String> cols = new ArrayList<>();
            List<String> values = new ArrayList<>();
            for (int i = 3; i < command.length; i++) {
                if (cols.size() == values.size()) {
                    if (command[i+1].equals("=")) {
                        cols.add(command[i]);
                        i++;
                    }
                    else {
                        throw new InvalidSyntaxException("Invalid Syntax");
                    }
                } else {
                    values.add(command[i].replace(",", ""));
                    if (!command[i].endsWith(",")) {
                        break;
                    }
                }
            }
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                if (cols.size() == values.size() && command.length == (3 * cols.size()) + 3) {
                    for (int i = 0; i < cols.size(); i++) {
                        table.updateWholeCol(cols.get(i), values.get(i));
                    }
                } else if (cols.size() == values.size() && command.length == (3 * cols.size()) + 7) {
                    updateSpecificParse(command, table, cols, values);
                } else {
                    throw new InvalidSyntaxException("Invalid Syntax");
                }
                table.saveTable();
            }
            else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }

    private void updateSpecificParse(String[] command, Table table, List<String> cols, List<String> values) throws InvalidSyntaxException {
        if (command[command.length - 4].equalsIgnoreCase("where") && command[command.length - 2].equals("=")) {
            String col = command[command.length - 3];
            String value = command[command.length - 1];
            if (table.colNames.contains(col)) {
                table.updateSpecific(col, value, cols, values);
            }
            else {
                System.out.println("Column not found");
            }
        }
        else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}