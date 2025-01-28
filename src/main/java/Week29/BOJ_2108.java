package Week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2108 {

    static int T;
    static int[] chapters;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        while(T --> 0) {
            int K = Integer.parseInt(br.readLine());
            String[] input = br.readLine().split(" ");
            chapters = new int[K+1];
            sum = new int[K+1];
            dp = new int[K+1][K+1];

            for(int i=0;i<K;i++) {
                chapters[i] = Integer.parseInt(input[i]);
            }
            for(int i=1;i<=K;i++) {
                sum[i] = sum[i-1] + chapters[i-1];
            }
            for(int d=1;d<K;d++) {
                for(int i=1;i + d<=K;i++) {
                    int y = i + d;
                    dp[i][y] = Integer.MAX_VALUE;
                    for(int mid=i;mid<y;mid++) {
                        dp[i][y] = Math.min(dp[i][y], dp[i][mid] + dp[mid+1][y] + sum[y] - sum[i-1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}
