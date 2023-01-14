import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public void executeCommand(String commandStr, Database database) {
        String[] command = commandStr.split(" ");
        if (command[0].equalsIgnoreCase("select")) {
            selectParser(command, database);
        } else if (command[0].equalsIgnoreCase("insert")) {
            insertParse(command, database);
        } else if (command[0].equalsIgnoreCase("update")) {
            updateParser(command, database);
        } else if (command[0].equalsIgnoreCase("delete")) {
            deleteParse(command, database);
        } else if (command[0].equalsIgnoreCase("create")) {
            createParse(command, database);
        } else {
            System.out.println("Unknown command.");
        }
    }

    private void insertParse(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("into") && command[3].equalsIgnoreCase("values")) {
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
                System.out.println("Table " + command[2] + " not found.");
            }
        } else {
            System.out.println("Unknown command");
        }
    }

    private void deleteParse(String[] command, Database database) {
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

    private void updateParser(String[] command, Database database) {
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
        } else {
            System.out.println("Unknown command.");
        }
    }

    private void selectParser(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("*")) {
            selectAllParser(command, database);
        } else {
            selectSpecificParser(command, database);
        }
    }

    private void selectAllParser(String[] command, Database database) {
        if (command[2].equalsIgnoreCase("from")) {
            String tableName = command[3];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                table.selectAll();
            } else {
                System.out.println("Table " + command[3] + " not found.");
            }
        } else {
            System.out.println("Unknown command.");
        }
    }

    private void selectSpecificParser(String[] command, Database database) {
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
                    System.out.println("Unknown command.");
                }

            } else {
                System.out.println("Table " + command[command.length - 1] + " not found.");
            }
        }
    }

    public void createParse(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("table") && command.length > 3) {
            String tableName = command[2];
            ArrayList<String> colNames = new ArrayList<>();
            for (int i = 3; i < command.length; i++) {
                String col = command[i];
                col = col.replace("(", "");
                col = col.replace(")", "");
                col = col.replace(",", "");
                colNames.add(col);
            }
            Table table = new Table(tableName, colNames);
            database.addTable(table);
        } else {
            System.out.println("Unknown command.");
        }
    }
}