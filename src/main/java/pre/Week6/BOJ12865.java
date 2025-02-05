package pre.Week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int[] W; // 무게 배열
    static int[] V; // 가치 배열
    static Integer[][] dp;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 개수
        int K = Integer.parseInt(st.nextToken()); // 최대 무게

        W = new int[N];
        V = new int[N];
        dp = new Integer[N][K+1];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N-1, K));
    }

    public static int knapsack(int n, int k){
        if(n < 0){
            return 0;
        }

        if(dp[n][k] == null){ // 탐색 하지 않은 경우
            if(W[n] > k){
                // 이전 값
                dp[n][k] = knapsack(n-1, k);
            }
            else{
                // 현재 물건 담을 수 있는 경우
                // 이전 n 가치 값과 현재 n 가치 값 중 큰 값 저장
                dp[n][k] = Math.max(knapsack(n-1, k), knapsack(n-1, k-W[n]) + V[n]);
            }
        }

        return dp[n][k];
    }

}
