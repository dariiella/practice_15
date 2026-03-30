import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.*;

public class practice_15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] texts = new String[50];
        LocalDateTime[] dates = new LocalDateTime[50];
        int n = 0;

        System.out.println("1 - новий щоденник");
        System.out.println("2 - відкрити файл");
        int start = sc.nextInt();
        sc.nextLine();

        if (start == 2) {
            System.out.println("введи шлях:");
            String path = sc.nextLine();

            try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;

                while ((line = br.readLine()) != null) {
                    if (!line.equals("")) {
                        dates[n] = LocalDateTime.parse(line);
                        texts[n] = br.readLine();
                        n++;
                    }
                }
                br.close();
            } catch (Exception e) {
                System.out.println("помилка читання");
            }
        }

        System.out.println("введи формат (наприклад dd.MM.yyyy HH:mm):");
        String f = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(f);

        int choice;

        do {
            System.out.println("1 - додати");
            System.out.println("2 - показати");
            System.out.println("0 - вихід");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                try {
                    System.out.println("дата:");
                    String d = sc.nextLine();

                    LocalDateTime dt = LocalDateTime.parse(d, formatter);

                    System.out.println("текст:");
                    String t = sc.nextLine();

                    dates[n] = dt;
                    texts[n] = t;
                    n++;

                } catch (DateTimeParseException e) {
                    System.out.println("неправильний формат!");
                }
            }

            if (choice == 2) {
                for (int i = 0; i < n; i++) {
                    System.out.println(dates[i].format(formatter));
                    System.out.println(texts[i]);
                    System.out.println();
                }
            }

        } while (choice != 0);

        System.out.println("зберегти? так/ні");
        String ans = sc.nextLine();

        if (ans.equals("yes")) {
            System.out.println("шлях:");
            String path = sc.nextLine();

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));

                for (int i = 0; i < n; i++) {
                    bw.write(dates[i].toString());
                    bw.newLine();
                    bw.write(texts[i]);
                    bw.newLine();
                    bw.newLine();
                }

                bw.close();
            } catch (Exception e) {
                System.out.println("помилка запису");
            }
        }
    }
}