package slidingwindow.BOJ_20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            solution(W, K);
        }
    }

    static void solution(String w, int k) {
        int[] cnt = new int[26];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            cnt[c - 'a']++;

            if (cnt[c - 'a'] >= k) {  // k번 이상 등장한 문자만 처리
                int len = 0;
                int idx = i;
                int count = 0;

                while (count != k && idx >= 0) {  // idx 범위 체크 추가
                    if (w.charAt(idx) == c) {
                        count++;
                    }
                    idx--;
                    len++;
                }

                if (count == k) {  // k번 찾은 경우에만 길이 갱신
                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }
        }

        if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min + " " + max);
        }
    }
}
