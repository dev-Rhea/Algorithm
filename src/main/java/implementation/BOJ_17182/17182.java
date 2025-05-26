package implementation.BOJ_17182;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static final int INF = 1_000_000_000;
    static int[][] time;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0;k<N;k++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                }
            }
        }

        int max = 1 << N;

        dp = new int[max][N];
        for(int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[1 << K][K] = 0;

        for(int i=0;i<max;i++) {
            for(int j=0;j<N;j++) {
                if((i & (1 << j)) == 0) continue;
                if(dp[i][j] == INF) continue;

                for(int k=0;k<N;k++) {
                    if((i & (1 << k)) != 0) continue;
                    int next = i | (1 << k);
                    dp[next][k] = Math.min(dp[next][k], dp[i][j] + time[j][k]);
                }
            }
        }

        int fin = (1 << N) - 1;
        int ans = INF;
        for(int i=0;i<N;i++) ans = Math.min(ans, dp[fin][i]);

        System.out.println(ans);
    }
}