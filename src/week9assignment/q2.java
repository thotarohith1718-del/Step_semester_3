package week9assignment;

import java.util.*;

public class q2 {
    public static ArrayList<Integer> mergeTokens(int[] counterA, int[] counterB) {
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result.add(counterA[i]);
                i++;
            } else {
                result.add(counterB[j]);
                j++;
            }
        }

        while (i < counterA.length) {
            result.add(counterA[i]);
            i++;
        }

        while (j < counterB.length) {
            result.add(counterB[j]);
            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int[] counterA = new int[m];

        for (int i = 0; i < m; i++) {
            counterA[i] = sc.nextInt();
        }

        int n = sc.nextInt();
        int[] counterB = new int[n];

        for (int i = 0; i < n; i++) {
            counterB[i] = sc.nextInt();
        }

        System.out.println(mergeTokens(counterA, counterB));
    }
}