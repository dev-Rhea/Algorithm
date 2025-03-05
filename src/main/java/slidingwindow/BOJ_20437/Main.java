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
        int[] cnt = new int[26]; // 알파벳 등장 횟수 저장
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);
            cnt[c - 'a']++; // 현재 문자의 알파벳 인덱스에 횟수 증가

            if (cnt[c - 'a'] >= k) {  // k번 이상 등장한 문자만 처리
                int len = 0; // 현재 부분 문자열 길이
                int idx = i; // 현재 인덱스에서 역으로 이동하여 K 번째 동일 문자 찾기
                int count = 0; // 현재 문자의 등장 횟수

                while (count != k && idx >= 0) {  // 현재 위치에서 거꾸로 이동
                    if (w.charAt(idx) == c) {
                        count++; // 동일 문자 발견
                    }
                    idx--; // 왼쪽으로 이동
                    len++; // 부분 문자열 증가
                }

                if (count == k) {  // k번 찾은 경우에만 길이 갱신
                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }
        }

        if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) { // 최대, 최소 갱신되지 않은 경우
            System.out.println(-1);
        } else {
            System.out.println(min + " " + max);
        }
    }
}
