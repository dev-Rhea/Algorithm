package DP.BOJ_2629;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] weights;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        dp = new boolean[31][15001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        // 가능한 무게 조합 계산 
        dp(0, 0);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            int m = Integer.parseInt(st.nextToken());
            if(m > 15000) sb.append("N ");
            else sb.append((dp[N][m]) ? "Y " : "N ");
        }
        System.out.println(sb);
    }
    
    // idx 번째 추와 조합하여 무게를 만들 수 있는지 확인 
    static void dp(int idx, int weight) {
        if(dp[idx][weight]) return;

        dp[idx][weight] = true;

        if(idx == N) return;

        dp(idx+1, weight);
        dp(idx+1, weight+weights[idx+1]); // 현재 추를 오른쪽에 추가 
        dp(idx+1, Math.abs(weight-weights[idx+1])); // 현재 추를 왼쪽에 추가 
    }
}