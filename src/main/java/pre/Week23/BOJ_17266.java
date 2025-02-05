package pre.Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17266 {
    static int N, M;
    static int[] X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        X = new int[M];

        // ㄱ가로등 위치 입력
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
            X[i] = Integer.parseInt(st.nextToken());
        }

        int dist = X[0];

        for (int i=1;i<M;i++) {
            dist = Math.max(dist, (X[i] - X[i - 1] + 1) / 2);
        }

        dist = Math.max(dist, (N - X[M - 1]));

        System.out.println(dist);

    }
}
