import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class DatabaseProgram {
    public static void main(String[] args) {
        System.out.println("Do you want to create table?\n1 - Yes\n2 - No");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();

        if (Objects.equals(answer, "2")) {
            System.out.println("Nauuuuura");
            exit(0);
        } else {
            System.out.println("Enter name of table:");
            String name = scanner.nextLine();
            System.out.println("Enter columns:");
            String colString = scanner.nextLine();
            String[] cols = colString.split(" ");
            Table table = new Table(name, cols);
            System.out.println("Input values:");
            String row1 = scanner.nextLine();
            String[] values = row1.split(" ");
            table.insert(values);
            table.print();
            table.saveTable();
        }

    }
}
