public class DeleteParser {
    public void deleteParse(String[] command, Database database) throws InvalidSyntaxException, TableNotFoundException{
        if (command[1].equalsIgnoreCase("from")) {
            String tableName = command[2];
            if (database.containsTable(tableName) && command.length == 3) {
                Table table = database.getTable(tableName);
                table.deleteAll();
                table.saveTable();
            } else if (database.containsTable(tableName) && command.length > 3) {
                System.out.println("using where command"); // to do maybe
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}
