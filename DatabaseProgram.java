import java.util.Scanner;
import static java.lang.System.exit;

public class DatabaseProgram {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Database database = new Database();
        Scanner sc = new Scanner(System.in);
        database.loadTables();
        if (database.isEmpty()) {
            System.out.println("Database doesn't have any table. Do you want to add one?\n1 - yes\n2 - no (quit)");
            String answer = sc.nextLine();
            if (answer.equals("1")) {
                database.addTable(new Table());
            }
            else {
                System.out.println("Shutting down...");
                exit(0);
            }
        }
        database.listTables();
        while (true) {
            System.out.println("1 - type command\n2 - create new table");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("2")) {
                database.addTable(new Table());
            } else if (answer.equalsIgnoreCase("1")){
                System.out.println("Type command:");
                String command = sc.nextLine();
                if (command.equalsIgnoreCase("quit")) {
                    System.out.println("Shutting down...");
                    exit(0);
                }
                else {
                    parser.executeCommand(command, database);
                }
            } else if (answer.equalsIgnoreCase("quit")) {
                System.out.println("Shutting down...");
                exit(0);
            } else {
                System.out.println("Unknown command.");
            }
        }

    }
}
