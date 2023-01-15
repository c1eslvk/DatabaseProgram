import java.util.ArrayList;
import java.util.List;

public class CreateParser {
    public void createParse(String[] command, Database database) throws InvalidSyntaxException{
        if (command[1].equalsIgnoreCase("table") && command.length > 3) {
            String tableName = command[2];
            List<String> colNames = new ArrayList<>();
            for (int i = 3; i < command.length; i++) {
                if (command[i].endsWith(",")) {
                    if (i == 3 && command[i].startsWith("(")) {
                        colNames.add(command[i].replace(",", "").replace("(", ""));
                    } else if (i != 3) {
                        colNames.add(command[i].replace(",", ""));
                    } else {
                        throw new InvalidSyntaxException("Invalid Syntax");
                    }
                } else {
                    if (command[i].endsWith(")")) {
                        colNames.add(command[i].replace(")", ""));
                        break;
                    } else {
                        throw new InvalidSyntaxException("Invalid Syntax");
                    }
                }
            }
            Table table = new Table(tableName, colNames);
            database.addTable(table);
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}
