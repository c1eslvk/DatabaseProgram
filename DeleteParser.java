public class DeleteParser {
    public void deleteParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command[1].equalsIgnoreCase("from")) {
            String tableName = command[2];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                if (command.length == 3) {
                    table.deleteAll();
                } else if (command.length == 7) {
                    deleteSpecificParse(command, table);
                } else {
                    throw new InvalidSyntaxException("Invalid Syntax");
                }
                table.saveTable();
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }

    private void deleteSpecificParse(String[] command, Table table) {
        if (command[3].equalsIgnoreCase("where") && command[5].equals("=")) {
            String col = command[4];
            String value = command[6];
            if (table.colNames.contains(col)) {
                table.deleteSpecific(col, value);
            }
            else {
                System.out.println("Column not found");
            }
        }
    }
}