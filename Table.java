import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;

public class Table {
    String name;
    ArrayList<String> colNames;
    ArrayList<HashMap<String, String>> rows = new ArrayList<>();

    public Table(String name, String[] cols) {
        this.name = name;
        colNames = new ArrayList<String>(Arrays.asList(cols));
    }

    public void saveTable() throws IOException {
        // not working :c
        String fileName = name + ".txt";
        File file = new File(fileName);
        file.createNewFile();
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
        for (String s : colNames) {
            fileWriter.write(s);
            fileWriter.write("|");
        }
        fileWriter.write("\n");
        for (HashMap<String, String> m : rows) {
            for (String s : colNames) {
                fileWriter.write(m.get(s));
                fileWriter.write("|");
            }
            fileWriter.write("\n");
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
