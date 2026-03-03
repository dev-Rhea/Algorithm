package 문자열.BOJ_1394;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int MOD = 900_528;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String pass = br.readLine();

        int k = input.length();
        int len = pass.length();
        long ans = 0;

        long pw = 1;
        for (int i = 0; i < len - 1; i++) {
            pw = (pw * k) % MOD;
            ans = (ans + pw) % MOD;
        }

        pw = 1;
        long pos = 0;
        for (int i = len - 1; i >= 0; i--) {
            int idx = input.indexOf(pass.charAt(i));
            pos = (pos + (long)(idx % MOD) * pw) % MOD;
            pw = (pw * k) % MOD;
        }
        pos = (pos + 1) % MOD;

        ans = (ans + pos) % MOD;
        System.out.println(ans);
    }
}