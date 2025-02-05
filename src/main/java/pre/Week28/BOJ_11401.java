package pre.Week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11401 {

    static int N, K;
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long number = factorial(N);
        long denom = factorial(K) * factorial(N - K) % MOD;



        System.out.println(number * pow(denom, MOD - 2) % MOD);
    }

    private static long factorial(int n) {
        long res = 1L;

        while (n > 1) {
            res = (res * n) % MOD;
            n--;
        }
        return res;
    }

    private static long pow(long base, long expo) {
        long res = 1;

        while(expo > 0) {
            if(expo % 2 == 1) {
                res *= base;
                res %= MOD;
            }
            base = (base * base) % MOD;
            expo /= 2;
        }
        return res;
    }
}
