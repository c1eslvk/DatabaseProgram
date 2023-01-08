import java.util.Scanner;
import static java.lang.System.exit;

public class DatabaseProgram {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Database database = new Database();
        Scanner sc = new Scanner(System.in);
        if (database.isEmpty()) {
            System.out.println("Database doesn't have any table. Do you want to add one?\n1 - yes\n2 - no");
            String answer = sc.nextLine();
            if (answer.equals("1")) {
                System.out.println("Name of table:");
                String name = sc.nextLine();

            }
            else {
                System.out.println("Ending program.");
                exit(0);
            }
        }
        String commandString = sc.nextLine();
        String[] command = commandString.split(" ");

    }
}
