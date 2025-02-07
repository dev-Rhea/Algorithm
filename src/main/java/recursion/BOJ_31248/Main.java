package recursion.BOJ_31248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long cnt;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        cnt = 0;
        sb = new StringBuilder();
        int idx = 0; // 시작점

        while(N >= 2) {
            hanoi(N - 2, idx, 2 - idx, 1);
            sb.append("ABC".charAt(idx)).append(" ").append("B").append("\n");
            sb.append("ABC".charAt(idx)).append(" ").append("D").append("\n");
            sb.append("B").append(" ").append("D").append("\n");
            cnt += 3;
            N -= 2;
            idx = 2 - idx;
        }

        if(N == 1) {
            sb.append("ABC".charAt(idx)).append(" ").append("D").append("\n");
            cnt++;
        }
        System.out.println(cnt);
        System.out.println(sb);
    }

    // i 원판 개수, s 시작, e 끝, v 중간
    private static void hanoi(int i, int s, int e, int v) {
        if(i == 0) return;

        hanoi(i-1, s, v, e);
        sb.append("ABC".charAt(s)).append(" ").append("ABC".charAt(e)).append("\n");
        hanoi(i-1, v, e, s);
        cnt++;
    }

}
