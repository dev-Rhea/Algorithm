package Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2563 {
    static int N;
    static int[][] map = new int[100][100]; // 색종이 수 100이하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 색종이 위치 입력 받기
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            // 색종이 붙이기
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    map[j][k] = 1;
                }
            }
        }

        int ans = 0;

        // 색종이 넓이
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}
