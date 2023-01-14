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

    public void updateWholeCol(String col, String val) {
        for (Map<String, String> row : rows) {
            row.replace(col, val);
        }
    }

    public void selectSpecific(List<String> cols) {
        if (checkCols(cols)) {
            System.out.print("| ");
            for (String col : cols) {
                System.out.print(col + " | ");
            }
            System.out.println();
            for (Map<String, String> row : rows) {
//                String.format("|%20s|", "abc")
                System.out.print("| ");
                for (String col : cols) {
                    System.out.print(row.get(col) + " | ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("Column not found.");
        }
    }

    public void selectAll() {
        System.out.print("| ");
        for (String col : colNames) {
            System.out.print(col + " | ");
        }
        System.out.println();
        for (Map<String, String> row : rows) {
            System.out.print("| ");
            for (String col : colNames) {
                System.out.print(row.get(col) + " | ");
            }
            System.out.println();
        }
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
