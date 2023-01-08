import java.util.ArrayList;
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
                System.out.println("Columns:");
                String columnsString = sc.nextLine();
                String[] columns = columnsString.split(" ");
                ArrayList<String> colNames = new ArrayList<>();
                for (String colName : columns) {
                    colNames.add(colName);
                }
                database.addTable(new Table(name, colNames));
            }
            else {
                System.out.println("Ending program.");
                exit(0);
            }
        }
        else {
            // show tables
            System.out.println("1 - type command\n2 - create new table");
        }
    }
}
