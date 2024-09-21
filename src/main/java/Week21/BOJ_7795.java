package Week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7795 {
    static int T, N, M;
    static int[] A, B;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];
            dp = new long[M + 1];

            st = new StringTokenizer(br.readLine());

            for (int i=0;i<N;i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);

            long answer = 0;

            for (int i = 0; i < N; i++) {
                answer += countSmaller(B, A[i]);
            }

            System.out.println(answer);
        }
    }

    static int countSmaller(int[] X, int Y) {
        int left = 0;
        int right = B.length;

        while (left < right) {
            int mid = (left+right) / 2;
            if(X[mid] < Y) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}
