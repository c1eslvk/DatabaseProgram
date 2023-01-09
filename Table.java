import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Table {
    public String name;
    ArrayList<String> colNames = new ArrayList<>();
    ArrayList<HashMap<String, String>> rows = new ArrayList<>();

    public Table() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of table:");
        this.name = sc.nextLine();
        System.out.println("Enter columns divided with space:");
        String[] colNames = sc.nextLine().split(" ");
        for (String colName : colNames) {
            this.colNames.add(colName);
        }
        this.saveTable();
        System.out.println("Table " + this.name + " has been created.");
    }
    public Table(String name, ArrayList<String> colNames) {
        this.name = name;
        //colNames = new ArrayList<String>(Arrays.asList(cols));
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
                HashMap<String, String> rowMap = new HashMap<>();
                for (int i = 0; i < colNames.size(); i++) {
                    rowMap.put(colNames.get(i), row[i]);
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
            if(!file.exists()) {
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            for (String s : colNames) {
                pw.print(s);
                pw.print(";");
            }
            pw.println();
            for (HashMap<String, String> m : rows) {
                for (String s : colNames) {
                    pw.print(m.get(s));
                    pw.print(";");
                }
                pw.println();
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(ArrayList<String> values) {
        HashMap<String, String> row = new HashMap<String, String>();

        for (int i = 0; i < colNames.size(); i++) {
            row.put(colNames.get(i), values.get(i));
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
