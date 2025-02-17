package implementation.BOJ_1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] cnt = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int start = 1;
        int len = 1; // 자릿수

        while (start <= N) {
            // 10의 배수가 될 때 까지 페이지 기록
            while (start % 10 != 0 && start <= N) cnt(start++, len);
            // 9에서 끝나도록 페이지 기록
            while (N % 10 != 9 && start <= N) cnt(N--, len);

            if(start > N) break;

            // 자릿수 조정
            start /= 10;
            N /= 10;
            // 자릿수에서 반복되는 수
            for(int i=0;i<10;i++) cnt[i] += (N - start + 1) * len;
            len *= 10; // 자릿수 증가
        }
        for(int n : cnt) {
            sb.append(n).append(' ');
        }
        System.out.println(sb);
    }

    // cnt 배열 갱신
    static void cnt(int n, int len) {
        while(n > 0) {
            cnt[n % 10] += len;
            n/= 10; // 자릿수 이동
        }
    }

}
