import java.io.*;
import java.util.*;

public class Table {
    public String name;
    List<String> colNames = new ArrayList<>();
    List<Map<String, String>> rows = new ArrayList<>();
    public Table(String name, List<String> colNames) {
        this.name = name;
        this.colNames = colNames;
    }
    public Table(String path) {
        try {
            this.name = path.replace(".txt", "");
            path = "database/" + path;
            File file = new File(path);
            Scanner sc = new Scanner(file);
            String columnsStr = sc.nextLine();
            String[] columns = columnsStr.split(";");
            for (String colName : columns) {
                colNames.add(colName);
            }
            while (sc.hasNextLine()) {
                String rowStr = sc.nextLine();
                String[] row = rowStr.split(";");
                Map<String, String> rowMap = new HashMap<>();
                int i = 0;
                for(String colName : colNames) {
                    rowMap.put(colName, row[i++]);
                }
                rows.add(rowMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTable() {
        try {
            String fileName = name + ".txt";
            File file = new File("database/" + fileName);
            if(file.exists() || file.createNewFile()) {
                execSaveFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void execSaveFile(File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (String col : colNames) {
                pw.print(col);
                pw.print(";");
            }
            pw.println();
            for (Map<String, String> m : rows) {
                for (String col : colNames) {
                    pw.print(m.get(col));
                    pw.print(";");
                }
                pw.println();
            }
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(List<String> values) {
        Map<String, String> row = new HashMap<>();

        for (int i = 0; i < colNames.size(); i++) {
            row.put(colNames.get(i), values.get(i));
        }
        rows.add(row);
    }

    public void deleteAll() {
        rows.clear();
    }
    public void deleteSpecific(String col, String value) {
        rows.removeIf(row -> row.get(col).equals(value));
    }

    public void updateWholeCol(String col, String val) {
        for (Map<String, String> row : rows) {
            row.replace(col, val);
        }
    }
    public void updateSpecific(String col, String val, List<String> cols, List<String> values) {
        for (Map<String, String> row : rows) {
            if (row.get(col).equals(val)) {
                for (int i = 0; i < cols.size(); i++) {
                    row.replace(cols.get(i), values.get(i));
                }
            }
        }
    }

    public void selectSpecificCols(List<String> cols) throws InvalidSyntaxException {
        if (checkCols(cols)) {
            System.out.print("|");
            for (String col : cols) {
                System.out.printf("%-10s|", col);
            }
            System.out.println();
            for (Map<String, String> row : rows) {
                System.out.print("|");
                for (String col : cols) {
                    System.out.printf("%-10s|", row.get(col));
                }
                System.out.println();
            }
        }
        else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
    public void selectSpecificColsWhere(List<String> cols, String col, String value) throws InvalidSyntaxException {
        if (checkCols(cols) && colNames.contains(col)) {
            System.out.print("|");
            for (String colName : cols) {
                System.out.printf("%-10s|", colName);
            }
            System.out.println();
            for (Map<String, String> row : rows) {
                if (row.get(col).equals(value)) {
                    System.out.print("|");
                    for (String colName : cols) {
                        System.out.printf("%-10s|", row.get(colName));
                    }
                    System.out.println();
                }

            }
        }
        else {
            throw new InvalidSyntaxException("Invalid Syntax");
        }
    }
    public void selectAll() {
        System.out.print("|");
        for (String col : colNames) {
            System.out.printf("%-10s|", col);
        }
        System.out.println();
        for (Map<String, String> row : rows) {
            System.out.print("|");
            for (String col : colNames) {
                System.out.printf("%-10s|", row.get(col));
            }
            System.out.println();
        }
    }
    public void selectAllWhere(String col, String value) {
        System.out.print("|");
        for (String colName : colNames) {
            System.out.printf("%-10s|", colName);
        }
        System.out.println();
        for (Map<String, String> row : rows) {
            if (row.get(col).equals(value)) {
                System.out.print("|");
                for (String colName : colNames) {
                    System.out.printf("%-10s|", row.get(colName));
                }
                System.out.println();
            }
        }
    }
    public void selectAllJoin(Table table, String condition) {
        List<String> tempCols = this.colNames;
        for (String col : table.colNames) {
            if (!col.equals(condition)) {
                tempCols.add(col);
            }
        }
        Table temp = new Table("temp", tempCols);
        List<Map<String, String>> tempRows = new ArrayList<>();
        int i = 0;
        for (Map<String, String> row1 : this.rows) {
            for (Map<String, String> row2 : table.rows) {
                if (row2.get(condition).equals(row1.get(condition))) {
                    tempRows.add(row1);
                    tempRows.get(i).putAll(row2);
                    i++;
                    break;
                }
            }
        }
        temp.rows = tempRows;
        temp.selectAll();
    }
    private boolean checkCols(List<String> cols) {
        for (String col : cols) {
            if (!colNames.contains(col)) {
                return false;
            }
        }
        return true;
    }
}
