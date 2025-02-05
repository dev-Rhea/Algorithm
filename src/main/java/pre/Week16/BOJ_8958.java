package pre.Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_8958 {
    static int N;
    static String[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            int ans = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (words[i].charAt(j) == 'O') {
                    cnt++;
                    ans += cnt;
                } else {
                    cnt = 0;
                }
            }
            System.out.println(ans);
        }
    }
}
