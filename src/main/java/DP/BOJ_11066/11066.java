package DP.BOJ_11066;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-->0) {
            int K = Integer.parseInt(br.readLine());

            int[] files = new int[K];
            int[] prefix = new int[K+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<K;i++) {
                files[i] = Integer.parseInt(st.nextToken());
                prefix[i+1] = prefix[i] + files[i];
            }

            int[][] dp = new int[K][K];

            for(int len=2;len<=K;len++) { // 구간 길이
                for(int i=0;i<=K-len;i++) { // 구간 시작 
                    int j = i+len-1; // 구간 끝 
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int mid=i;mid<j;mid++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid+1][j]+prefix[j+1]-prefix[i]);
                    }
                }
            }

            sb.append(dp[0][K-1]).append('\n');
        }

        System.out.print(sb);
    }
}