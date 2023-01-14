public class DeleteParser {
    public void deleteParse(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("from")) {
            String tableName = command[2];
            if (database.containsTable(tableName) && command.length == 3) {
                Table table = database.getTable(tableName);
                table.deleteAll();
                table.saveTable();
            } else if (database.containsTable(tableName) && command.length > 3) {
                System.out.println("using where command"); // to do maybe
            } else {
                System.out.println("Table " + command[2] + " not found.");
            }
        } else {
            System.out.println("Unknown command");
        }
    }
}
