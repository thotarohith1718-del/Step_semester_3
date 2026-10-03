package week9practice;

import java.util.*;

public class q1 {
    public static String findBook(String[][] catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int result = catalog[mid][0].compareTo(targetIsbn);

            if (result == 0)
                return catalog[mid][1];
            else if (result < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        String[][] catalog = new String[n][2];

        for (int i = 0; i < n; i++) {
            catalog[i][0] = sc.nextLine();
            catalog[i][1] = sc.nextLine();
        }

        String targetIsbn = sc.nextLine();

        System.out.println(findBook(catalog, targetIsbn));
    }
}