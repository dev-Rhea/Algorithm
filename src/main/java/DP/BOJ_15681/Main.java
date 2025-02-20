package DP.BOJ_15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, root, Q;
    static int[] dp;
    static ArrayList<Integer>[] tree; // 트리 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        tree = new ArrayList[N+1];

        for(int i=0;i<=N;i++) {
            tree[i] = new ArrayList<>();
            dp[i] = -1;
        }

        int a, b;
        for(int i=1;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            tree[a].add(b); // a의 자식 노드 b
            tree[b].add(a); // 무방향 트리이므로 b의 인접리스트로 a 추가
        }

        dfs(root);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<Q;i++) {
            // 입력받은 노드 번호에 해당하는 dp 값 출력
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    static int dfs(int node) {
        if(dp[node] != -1) return 0; // 계산된 노드는 재귀하지 않음

        dp[node] = 1; // 자기 자신을 포함하므로 1로 초기화

        // 자신에게 연결된 모든 노드 순회
        for(int next : tree[node]) {
            dp[node] += dfs(next); // 자식 노드로 재귀한 값을 저장
        }
        return dp[node];
    }
}
