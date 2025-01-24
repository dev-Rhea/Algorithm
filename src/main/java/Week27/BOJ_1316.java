package Week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1316 {

    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];
        int cnt = 0;

        // 단어 입력
        for (int i = 0; i < N; i++) {
            if (check(br.readLine())) {
                cnt++;
            }

        }
        System.out.println(cnt);
    }

    static boolean check(String str) {
        boolean alphabet[] = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            int now = str.charAt(i);
            int prev = (i > 0) ? str.charAt(i - 1) : 0;

            if (!alphabet[now - 'a']) {
                alphabet[now - 'a'] = true;
            } else {
                if (now != prev) {
                    return false;
                }
            }
        }
        return true;
    }
}
