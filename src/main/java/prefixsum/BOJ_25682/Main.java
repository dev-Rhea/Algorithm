package prefixsum.BOJ_25682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if ((i + j) % 2 == 0) {
                    map[i][j] = (input.charAt(j) == 'W') ? 0 : 1; // W: 0, B: 1 흰색 부터 시작
                } else {
                    map[i][j] = (input.charAt(j) == 'W') ? 1 : 0;
                }
            }
        }
        System.out.println(cutting(N, M, K, map));
    }

    static int cutting(int n, int m, int size, int[][] map) {
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) {
                    map[i][j] += map[i - 1][j]; // 현재까지 위치의 합
                }
                if (j > 0) {
                    map[i][j] += map[i][j - 1]; // 왼쪽칸과 위쪽 칸 더함
                }
                if (i > 0 && j > 0) {
                    map[i][j] -= map[i - 1][j - 1]; // 중복제거
                }
            }
        }

        int total; // 현재 K 영역의 합
        // K 영역 내에서 최소값 찾기
        for (int i = size - 1; i < n; i++) {
            for (int j = size - 1; j < m; j++) {
                total = map[i][j];
                if (i - size + 1 > 0) {
                    total -= map[i - size][j];
                }
                if (j - size + 1 > 0) {
                    total -= map[i][j - size];
                }
                if (i - size + 1 > 0 && j - size + 1 > 0) {
                    total += map[i - size][j - size];
                }
                ans = Math.min(ans, Math.min(total, reverse(size, total))); // 반대 패턴 계산까지 비교
            }
        }
        return ans;
    }

    static int reverse(int size, int total) {
        int mid = K * K / 2;
        if (total > mid) {
            return K * K - total;
        } else {
            return total;
        }
    }
}
