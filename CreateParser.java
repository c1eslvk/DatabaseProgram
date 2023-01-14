import java.util.ArrayList;
import java.util.List;

public class CreateParser {
    public void createParse(String[] command, Database database) {
        if (command[1].equalsIgnoreCase("table") && command.length > 3) {
            String tableName = command[2];
            List<String> colNames = new ArrayList<>();
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
