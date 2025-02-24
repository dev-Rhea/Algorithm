package etc.kmp.BOJ_10266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int max = 360000; // 원
        int len = max << 1; // 회전 시킨 원
        int[] fail = new int[max]; // 실패 함수
        boolean[] A = new boolean[len]; // 패턴
        boolean[] B = new boolean[len]; // 텍스트

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 첫번째 시계
            B[Integer.parseInt(st.nextToken())] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 두번째 시계
            A[Integer.parseInt(st.nextToken())] = true;
        }

        // 회전 비교
        for (int i = 0, j = max; i < max; ) {
            B[j++] = B[i++];
        }

        for (int i = 1, j = 0; i < max; i++) {
            while (0 < j && A[i] != A[j]) {
                j = fail[j - 1]; // 실패 함수
            }
            // 같은 경우
            if (A[i] == A[j]) {
                fail[i] = ++j;
            }
        }

        // 동일한지 확인
        for (int i = 0, j = 0; i < len; i++) {
            while (0 < j && B[i] != A[j]) {
                j = fail[j - 1]; // 실패 함수
            }
            // 같은 경우
            if (B[i] == A[j]) {
                j++;
                if (j == max) {
                    System.out.println("possible");
                    return;
                } else {
                    j++;
                }
            }
        }
        System.out.println("impossible");
    }

}
