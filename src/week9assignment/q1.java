import java.util.*;

public class Main {
    public static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = 0;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }

            if (total > bestTotal) {
                bestTotal = total;
                bestRow = i;
            }
        }

        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] marks = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = sc.nextInt();
            }
        }

        int[] result = findTopper(marks);

        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}