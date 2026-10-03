package week9assignment;

import java.util.*;

public class q4 {
    public static int countAlerts(int[] readings, int k, int threshold) {
        int sum = 0;
        int count = 0;

        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        if (sum >= k * threshold) {
            count++;
        }

        for (int i = k; i < readings.length; i++) {
            sum += readings[i];
            sum -= readings[i - k];

            if (sum >= k * threshold) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] readings = new int[n];

        for (int i = 0; i < n; i++) {
            readings[i] = sc.nextInt();
        }

        int k = sc.nextInt();
        int threshold = sc.nextInt();

        System.out.println(countAlerts(readings, k, threshold));
    }
}