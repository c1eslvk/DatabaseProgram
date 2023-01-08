import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Table {
    String name;
    ArrayList<String> colNames;
    ArrayList<HashMap<String, String>> rows = new ArrayList<>();

    public Table(String name, String[] cols) {
        this.name = name;
        colNames = new ArrayList<String>(Arrays.asList(cols));
    }

    public void saveTable() {
        try {
            String fileName = name + ".txt";
            File file = new File(fileName);
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            for (String s : colNames) {
                pw.print(s);
                pw.print("|");
            }
            pw.println();
            for (HashMap<String, String> m : rows) {
                for (String s : colNames) {
                    pw.print(m.get(s));
                    pw.print("|");
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(String[] values) {
        HashMap<String, String> row = new HashMap<String, String>();

        for (int i = 0; i < colNames.size(); i++) {
            row.put(colNames.get(i), values[i]);
        }
        rows.add(row);
    }

    public void print() {
        System.out.print("| ");
        for (String col : colNames) {
            System.out.print(col + " | ");
        }
        System.out.println();
        for (HashMap<String, String> m : rows) {
            System.out.print("| ");
            for (String col : colNames) {
                System.out.print(m.get(col) + " | ");
            }
            System.out.println();
        }
    }
}
