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
            System.out.println("Type command:");
            String command = sc.nextLine();
            if (command.split(" ")[0].equalsIgnoreCase("create")) {
                parser.createParse(command, database);
            }
            else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
                System.out.println("Shutting down...");
                exit(0);
            }
            else {
                parser.executeCommand(command, database);
            }
        }

    }
}
