import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long result = countOnes(B) - countOnes(A - 1);
        System.out.println(result);
    }

    public static long countOnes(long n) {
        long res = 0;
        long digit = 1;

        while (digit <= n) {
            long high = n / (digit * 2);
            long cur = (n / digit) % 2;
            long low = n % digit;

            if (cur == 1) {
                res += high * digit + (low + 1);
            } else {
                res += high * digit;
            }

            digit *= 2;
        }

        return res;
    }
}
