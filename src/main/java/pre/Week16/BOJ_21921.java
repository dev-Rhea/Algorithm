package pre.Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921 {
    static int N, X;
    static int[] visitors;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visitors = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            visitors[i] = Integer.parseInt(st.nextToken());
        }

        sum = new int[N+1];

        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i-1] + visitors[i];
        }

        int max = 0;

        for(int i = X; i <= N; i++) {
            max = Math.max(max, sum[i] - sum[i-X]);
        }

        int cnt = 0;

        for(int i = X; i <= N; i++) {
            if(sum[i] - sum[i-X] == max) {
                cnt++;
            }
        }

        if(max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
