import java.util.Scanner;
import static java.lang.System.exit;

public class DatabaseProgram {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Database database = new Database();
        Scanner sc = new Scanner(System.in);
        database.loadTables();
        database.listTables();
        while (true) {
            System.out.println("Type command:");
            String command = sc.nextLine();
            if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("q")) {
                System.out.println("Shutting down...");
                exit(0);
            }
            else {
                try {
                    parser.executeCommand(command, database);
                } catch (InvalidSyntaxException | TableNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
