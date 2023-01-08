import java.util.ArrayList;

public class Parser {

    public void executeCommand(String[] command, Table table) {
        if (command[0].equalsIgnoreCase("select")) {

        }
        else if (command[0].equalsIgnoreCase("insert")) {

        }
        else if (command[0].equalsIgnoreCase("update")) {

        }
        else if (command[0].equalsIgnoreCase("delete")) {

        }
        else {
            System.out.println("Unknown command.");
        }
    }

    public Table createTable(String[] command) {
        if (command[1].equalsIgnoreCase("table")) {
            String name = command[2];
            ArrayList<String> colNames = new ArrayList<>();
            for (int i = 3; i < command.length; i++) {
                command[i] = command[i].replace(",", "");
                command[i] = command[i].replace("(", "");
                command[i] = command[i].replace(")", "");
                colNames.add(command[i]);
            }
            System.out.println("Table " + name + " has been created.");
            return new Table(name, colNames);
        }
        else {
            System.out.println("Unknown command.");
            return null;
            //change to exception
        }
    }


}
