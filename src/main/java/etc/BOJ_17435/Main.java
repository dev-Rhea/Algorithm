package etc.BOJ_17435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int logN = (int) (Math.log(500000) / Math.log(2)) + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int[][] function = new int[logN + 1][m + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            function[0][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= logN; i++) {
            for (int j = 1; j <= m; j++) {
                function[i][j] = function[i - 1][function[i - 1][j]];
            }
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            for (int i = 0; i <= logN; i++) {
                if ((n & (1 << i)) > 0) {
                    x = function[i][x];
                }
            }
            sb.append(x).append("\n");
        }
        System.out.print(sb);
    }
}
