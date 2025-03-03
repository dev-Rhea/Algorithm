package dfs.BOJ_3584.BOJ_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int INF = Integer.MAX_VALUE;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[2 * K];
        Arrays.fill(dp, INF);
        answer = INF;

        findPath(K, 0);
        System.out.println(answer);
    }

    static void findPath(int k, int cnt) {
        // k 현재 위치, cnt 이동 횟수

        if (k <= N) {
            answer = Math.min(answer, cnt + N - k);
            return;
        }

        // 현재 경로가 최소 경로보다 크고, 불가능한 음수 좌표 거나, 이미 최소 경로가 있다면 중지
        if (cnt >= answer || k < 0 || dp[k] < cnt) {
            return;
        }
        dp[k] = cnt; // 최소 경로 갱신
        if (k % 2 == 0) { // 순간이동
            findPath(k / 2, cnt); // 순간이동은 시간이 0이므로 cnt 증가 안함
        }
        findPath(k - 1, cnt + 1); // 뒤로 한칸
        findPath(k + 1, cnt + 1); // 앞으로 한칸
    }
}
