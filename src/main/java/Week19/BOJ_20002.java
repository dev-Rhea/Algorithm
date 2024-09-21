package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20002 {
    static int N, K;
    static int[][] map;
    static int[][] sum;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        sum = new int[N+1][N+1];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + map[i-1][j-1]; // k * K 사과 총이익
            }
        }

        // 가능한 모든 부분 행렬의 합 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < Math.min(N - i, N - j); k++) {
                    // 부분 행렬의 합을 계산
                    int temp = sum[i + k + 1][j + k + 1] - sum[i][j + k + 1] - sum[i + k + 1][j] + sum[i][j];
                    max = Math.max(max, temp); // 최대값 갱신
                }
            }
        }

        System.out.println(max);
    }
}
