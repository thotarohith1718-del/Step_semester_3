package week9assignment;

import java.util.*;

public class q3 {
    public static String[] mostPopular(String[] orders) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String item : orders) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = map.get(bestItem);

        for (String item : orders) {
            if (map.get(item) > bestCount) {
                bestItem = item;
                bestCount = map.get(item);
            }
        }

        return new String[]{bestItem, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] orders = new String[n];

        for (int i = 0; i < n; i++) {
            orders[i] = sc.next();
        }

        String[] result = mostPopular(orders);

        System.out.println("(\"" + result[0] + "\", " + result[1] + ")");
    }
}