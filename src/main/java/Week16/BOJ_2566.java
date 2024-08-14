package Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2566 {
    public  static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[9][9];
        int max = 0;
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < 9; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
                if (arr[i][j] > max) {
                    max = arr[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println(max);
        System.out.println((maxRow + 1) + " " + (maxCol + 1));
    }
}
