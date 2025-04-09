package DP.BOJ_9527;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long[] dp = new long[55]; // 1의 갯수 저장 ()
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        dp[0] = 1;
        // dp[i-1] -> 0 ~ (2^i - 1) 범위의 1의 갯수 총합, << 1L 은 2배 연산  => i 비트 자리로 올라 갔을 때, 기존 값에 '0'을 붙인 경우가 생기는데 이 수들은 기존 범위와 같은 1의 개수임
        // (1L << i) -> 2^i => 새로운 상위 비트에서 추가된 1의 갯수 계산 
        for(int i=1;i<55;i++) dp[i] = (dp[i-1] << 1L) + (1L <<i);

        System.out.println(calculate(B) - calculate(A - 1L));
    }

    static long calculate(long n) {
        long ans = n & 1; // 마지막 비트가 1인지 확인 

        // 최상위 비트 부터 탐색 
        for(int i=54;i>0;i--) {
            // i 번째 비트가 1이면 
            if((n & (1L << i)) != 0) {
                n -= 1L << i; // 0으로 만듦
                ans += dp[i - 1] + (n + 1); // 2^i 보다 작은 수들의 1의 갯수 + 현재 상태에서 최상위비트가 1인 숫자 갯수 
            }
        }
        return ans;
    }
}