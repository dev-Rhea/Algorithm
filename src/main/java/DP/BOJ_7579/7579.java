package DP.BOJ_7579;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 100 * N;
        int[] dp = new int[sum + 1]; // j 만큼 썻을 때 확보할 수 있는 최대 메모리 저장 

        // 앱 하나당 누적 비용      
        for(int i=0;i<N;i++) {
            for(int j=sum;j>=cost[i];j--) {
                // 이 앱을 비활성화하지 않는 경우와 이  앱을 비활성화 하는 경우 비교 
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }

        for(int i=0;i<=sum;i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}