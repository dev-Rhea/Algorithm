package backtracking.BOJ_16938;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int[] A;
    static int N, L, R, X, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        ans = 0;

        cal(1, 0, 0,Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println(ans);
    }

    static void cal(int idx, int cnt, int sum, int max, int min) {
        if(cnt >= 2) {
            if(sum >= L && sum <= R && max - min >= X) ans += 1;
        }

        for(int i=idx;i<=N;i++) {

            cal(i+1, cnt+1, sum + A[i], Math.max(A[i], max), Math.min(A[i], min));
        }
    }
}