import java.util.ArrayList;

public class UpdateParser {
    public void updateParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command[2].equalsIgnoreCase("set")) {
            String tableName = command[1];
            int colCounter = 0, valCounter = 0;
            ArrayList<String> cols = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            for (int i = 3; i < command.length; i++) {
                if (!command[i].equalsIgnoreCase("=")) {
                    if (colCounter == valCounter) {
                        cols.add(command[i]);
                        colCounter++;
                    } else {
                        values.add(command[i]);
                        valCounter++;
                    }
                }
            }
            if (database.containsTable(tableName) && cols.size() == values.size()) {
                Table table = database.getTable(tableName);
                for (int i = 0; i < cols.size(); i++) {
                    table.updateWholeCol(cols.get(i), values.get(i));
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
}
