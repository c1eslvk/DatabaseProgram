import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public void executeCommand(String commandStr, Database database) {
        String[] command = commandStr.split(" ");
        if (command[0].equalsIgnoreCase("select")) {

        }
        else if (command[0].equalsIgnoreCase("insert")) {
            insert(command, database);
        }
        else if (command[0].equalsIgnoreCase("update")) {

        }
        else if (command[0].equalsIgnoreCase("delete")) {

        }
        else {
            System.out.println("Unknown command.");
        }
    }

    public void insert(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("into") && command[3].equalsIgnoreCase("values")) {
            String tableName = command[2] + ".txt";
            if (database.containsTable(tableName)) {
                ArrayList<String> values = new ArrayList<>();
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
                    System.out.println("Inserted values into:" + table.name);
                }
                else {
                    System.out.println("Not enough arguments.");
                }
            }
            else {
                System.out.println("Table " + tableName + " not found.");
            }
        }
        else {
            System.out.println("Unknown command");
        }
    }
}
