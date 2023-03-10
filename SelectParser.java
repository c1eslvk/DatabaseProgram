import java.util.ArrayList;
import java.util.List;

public class SelectParser {
    public void selectParse(String[] command, Database database)
            throws InvalidSyntaxException, TableNotFoundException {
        if (command[1].equalsIgnoreCase("*")) {
            selectAllParser(command, database);
        } else {
            selectSpecificParser(command, database);
        }
    }

    private void selectAllParser(String[] command, Database database)
            throws InvalidSyntaxException, TableNotFoundException {
        if (command[2].equalsIgnoreCase("from")) {
            String tableName = command[3];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                if (command.length == 4) {
                    table.selectAll();
                } else if (command.length == 8) {
                    selectAllWhereParse(command, table);
                } else if (command.length == 10) {
                    selectJoinParse(command, table, database);
                } else {
                    throw new InvalidSyntaxException("Invalid Syntax");
                }
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
    private void selectAllWhereParse(String[] command, Table table) {
        if (command[4].equalsIgnoreCase("where") && command[6].equals("=")) {
            String col = command[5];
            String value = command[7];
            if (table.colNames.contains(col)) {
                table.selectAllWhere(col, value);
            }
            else {
                System.out.println("Column not found");
            }
        }
    }
    private void selectSpecificParser(String[] command, Database database)
            throws InvalidSyntaxException, TableNotFoundException{
        List<String> cols = new ArrayList<>();
        for (int i = 1; i < command.length; i++) {
            if (command[i].endsWith(",")) {
                cols.add(command[i].replace(",", ""));
            } else {
                cols.add(command[i]);
                break;
            }
        }
        int numOfCols = cols.size();
        if (command[1 + numOfCols].equals("from")) {
            String tableName = command[2 + numOfCols];
            if (database.containsTable(tableName)) {
                Table table = database.getTable(tableName);
                if(command.length == numOfCols + 3) {
                    table.selectSpecificCols(cols);
                } else if (command.length == numOfCols + 7) {
                    selectSpecificWhereParse(command, table, cols);
                }
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
    private void selectSpecificWhereParse(String[] command, Table table, List<String> cols)
            throws InvalidSyntaxException {
        if (command[command.length - 4].equalsIgnoreCase("where") && command[command.length - 2].equals("=")) {
            String col = command[command.length - 3];
            String value = command[command.length - 1];
            table.selectSpecificColsWhere(cols, col, value);
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
    private void selectJoinParse(String[] command, Table table, Database database)
            throws InvalidSyntaxException, TableNotFoundException {
        if (command[4].equalsIgnoreCase("join") && command[6].equalsIgnoreCase("on") && command[8].equals("=")) {
            String table2Name = command[5];
            String[] condition1 = command[7].split("\\.");
            String[] condition2 = command[9].split("\\.");
            if (database.containsTable(table2Name)) {
                if (condition1.length == 2 && condition2.length == 2 && condition1[0].equals(table.name) &&
                        condition2[0].equals(table2Name) && condition1[1].equals(condition2[1])) {
                    Table table2 = database.getTable(table2Name);
                    if (table.colNames.contains(condition1[1]) && table2.colNames.contains(condition2[0])) {
                        table.selectAllJoin(table2, condition1[1]);
                    } else {
                        throw new InvalidSyntaxException("Invalid Syntax");
                    }

                } else {
                    throw new InvalidSyntaxException("Invalid Syntax");
                }
            } else {
                throw new TableNotFoundException("Table not found");
            }
        } else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
}