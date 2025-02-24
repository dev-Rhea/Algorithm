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
        int max = 360000;
        int len = max << 1;
        int[] fail = new int[max];
        boolean[] A = new boolean[len];
        boolean[] B = new boolean[len];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[Integer.parseInt(st.nextToken())] = true;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 0, j = max; i < max; ) {
            B[j++] = B[i++];
        }

        for (int i = 1, j = 0; i < max; i++) {
            while (0 < j && A[i] != A[j]) {
                j = fail[j - 1];
            }
            if (A[i] == A[j]) {
                fail[i] = ++j;
            }
        }

        for (int i = 0, j = 0; i < len; i++) {
            while (0 < j && B[i] != A[j]) {
                j = fail[j - 1];
            }
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
