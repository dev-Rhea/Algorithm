package divideandconquer.BOJ_11444;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long MOD = 1000000007;
    static long[][] original = {{0, 1}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        System.out.println(fi(N)[0][1]);
    }

    static long[][] fi(long n) {
        if (n == 1) return original;
        
        long[][] half = fi(n / 2);
        long[][] result = pow(half);

        if (n % 2 == 1) {
            result = multiply(result, original);
        }

        return result;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] temp = new long[2][2];

        temp[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        temp[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        temp[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        temp[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;

        return temp;
    }

    static long[][] pow(long[][] m) {
        return multiply(m, m);
    }
}
