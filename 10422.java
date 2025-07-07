import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int MOD = 1_000_000_007;
        long[] dp = new long[5001];
        dp[0] = 1;
        dp[2] = 1;

        for(int i=4;i<5001;i+=2) {
            for(int j=0;j<i;j+=2) {
                dp[i] += dp[j] * dp[i-j-2];
                dp[i] %= MOD;
            }
        }

        for(int i=0;i<T;i++) {
            int L = Integer.parseInt(br.readLine());

            System.out.println(dp[L]);
        }
    }
}