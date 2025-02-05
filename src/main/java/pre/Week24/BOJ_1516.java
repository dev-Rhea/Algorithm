package pre.Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1516 {
    static int N;
    static int[] dp;
    static int[] time;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        time = new int[N+1];
        arr = new ArrayList[N+1];

        for(int i=0;i<=N;i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            time[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if(num == -1) break;

                arr[i].add(num);
            }
        }

        for(int i=1;i<=N;i++) {
            dfs(i);
            System.out.println(dp[i]);
        }
    }

    public static int dfs(int n) {
        if(dp[n] != 0) return dp[n];
        dp[n] += time[n];
        int max = 0;

        for(int a : arr[n]) {
            max = Math.max(max, dfs(a));
        }
        dp[n] += max;
        return dp[n];
    }
}
